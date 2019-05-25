package net.devaction.pubnumdemo.main;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pubnub.api.PubNub;
import com.pubnub.api.endpoints.pubsub.Publish;

import net.devaction.pubnumdemo.config.ApplicationConfiguration;
import net.devaction.pubnumdemo.config.ConfigurationReader;
import net.devaction.pubnumdemo.core.TweetProcessorImpl;
import net.devaction.pubnumdemo.core.CountryData;
import net.devaction.pubnumdemo.core.CountryMapConstructor;
import net.devaction.pubnumdemo.core.Message;
import net.devaction.pubnumdemo.publisher.MessagePublishedCallback;
import net.devaction.pubnumdemo.publisher.PubNubConstructor;
import net.devaction.pubnumdemo.publisher.PublisherRunnable;
import net.devaction.pubnumdemo.twitter.CountryResolver;
import net.devaction.pubnumdemo.twitter.ListenerRunnablesConstructor;
import net.devaction.pubnumdemo.twitter.StatusListenerImpl;
import net.devaction.pubnumdemo.twitter.StatusProcessorImpl;
import net.devaction.pubnumdemo.twitter.StreamConstructor;
import net.devaction.pubnumdemo.twitter.TwitterStreamListenerRunnable;
import twitter4j.TwitterStream;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
@SuppressWarnings("restriction")
public class PublisherMain implements SignalHandler{
    private static final Logger log = LoggerFactory.getLogger(PublisherMain.class);
    
    private static final String WINCH_SIGNAL = "WINCH";
    
    private PubNub pubnub;
    
    private List<TwitterStream> twitterStreams;
    
    public static void main(String[] args){
        new PublisherMain().run();
    }
    
    private void run(){
        ApplicationConfiguration configuration = null;
        try{
            configuration = new ConfigurationReader().read();
        } catch (Exception ex){
            log.error("FATAL: unable to read configuration from file, exiting.", ex);
            return;
        } 
        
        BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>(
                configuration.getBlockingQueueCapacity());
        
        TweetProcessorImpl tweetProcessor = new TweetProcessorImpl(queue);
        Map<String,CountryData> countryMap = CountryMapConstructor.construct(configuration.getCountries());
        
        tweetProcessor.setCountryMap(countryMap);
        
        StatusProcessorImpl statusProcessor = new StatusProcessorImpl();
        statusProcessor.setTweetProcessor(tweetProcessor);        
      
        ListenerRunnablesConstructor runnablesConstructor = new ListenerRunnablesConstructor(configuration.getCountries());
        runnablesConstructor.setStatusProcessor(statusProcessor);
        
        CountryResolver countryResolver = new CountryResolver(configuration.getCountries());
        runnablesConstructor.setCountryResolver(countryResolver);
        
        List<StreamConstructor> streamConstructors = new StreamConstructorsConstructor()
                .construct(configuration.getCountries());
        runnablesConstructor.setStreamConstructors(streamConstructors);
            
        MessagePublishedCallback messagePublishedCallback = new MessagePublishedCallback();
        
        PublisherRunnable publisherRunnable = new PublisherRunnable(queue);
        
        PubNubConstructor pubNubConstructor = new PubNubConstructor();        
        pubNubConstructor.setPublishKey(configuration.getPubnumPublishKey());
        pubNubConstructor.setSubscribeKey(configuration.getPubnumSubscribeKey());
        pubnub = pubNubConstructor.construct();
        
        Publish publish = pubnub.publish();
        publisherRunnable.setPublish(publish);
        publisherRunnable.setCallback(messagePublishedCallback);        
        
        Thread publisherThread = new Thread(publisherRunnable);
        publisherThread.setName("pubNubPublisher_thread");        
        
        // To be able to shutdown the application gracefully on a Linux system
        registerThisAsOsSignalHandler();
        
        publisherThread.start();

        twitterStreams = runnablesConstructor.construct();
    }
    
    private void registerThisAsOsSignalHandler(){
        log.debug("Going to register this object to handle the " + WINCH_SIGNAL + " signal");
        try{
            Signal.handle(new Signal(WINCH_SIGNAL), this);
        } catch(Throwable throwable){
            // Most likely this is a signal that's not supported on this
            // platform or with the JVM as it is currently configured
            log.error("FATAL: The signal is not supported: {}", WINCH_SIGNAL, throwable);
            throw throwable;
        }        
    }

    @Override
    public void handle(Signal signal){
        log.info("Signal {} has been captured.", signal.getName());
        closeTwitter(twitterStreams);
        sleep(1);
        closePubNub(pubnub);
        sleep(1);
        log.info("Exiting application");  
        System.exit(0);
    }
    
    private void closeTwitter(List<TwitterStream> streams){
        int i = 0;
        for (TwitterStream stream: streams) {
            i++;
            log.info("Going to clean up Twitter Web API resources of the stream number {}", i);
            stream.cleanUp(); 
            sleep(1);
            log.info("Going to shutdown the Twitter stream number {}", i);
            stream.shutdown();
        }
    }
    
    private void closePubNub(PubNub pubnub){
        if (pubnub == null)
            return;
        
        log.debug("Going to call PubNub disconnect method"); 
        pubnub.disconnect();
        sleep(1);
        log.debug("Going to call PubNub destroy method");         
        pubnub.destroy();
        sleep(1);
        log.debug("Going to call PubNub forceDestroy method"); 
        pubnub.forceDestroy();
    }    
    
    private void sleep(int secondsNum){        
        try{
            TimeUnit.SECONDS.sleep(secondsNum);
        } catch (InterruptedException ex){
            log.error(ex.toString(), ex);
        }
    }    
}
