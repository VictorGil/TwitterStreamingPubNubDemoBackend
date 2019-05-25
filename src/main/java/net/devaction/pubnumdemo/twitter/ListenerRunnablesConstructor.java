package net.devaction.pubnumdemo.twitter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devaction.pubnumdemo.config.Country;
import net.devaction.pubnumdemo.util.NameableThreadFactory;
import twitter4j.TwitterStream;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class ListenerRunnablesConstructor{
    private static final Logger log = LoggerFactory.getLogger(ListenerRunnablesConstructor.class);
    
    private final Country[] countries;
    
    // It needs to be injected
    private CountryResolver countryResolver;
    
    // It needs to be injected
    private StatusProcessor statusProcessor;
    
    // It needs to be injected
    private List<StreamConstructor> streamConstructors;
    
    public ListenerRunnablesConstructor(Country[] countries){
        this.countries = countries;
    }
    
    public List<TwitterStream> construct(){
        ThreadFactory  threadFactory = new NameableThreadFactory("listenerThread");        
        final ExecutorService executorService = Executors.newFixedThreadPool(countries.length, threadFactory);
                
        final List<TwitterStream> streamList = new LinkedList<TwitterStream>();
        
        final Iterator<StreamConstructor> streamConstructorIter = streamConstructors.iterator();
        
        for (int i = 0; i < countries.length; i++){
            Country country = countries[i];
            
            StatusListenerImpl listener = new StatusListenerImpl();            
            listener.setCountryName(country.getName());
            listener.setCountryResolver(countryResolver);
            listener.setStatusProcessor(statusProcessor);
            
            TwitterStreamListenerRunnable runnable = new TwitterStreamListenerRunnable(country);
            runnable.setListener(listener);            

            StreamConstructor streamConstructor = streamConstructorIter.next();
            TwitterStream stream = streamConstructor.constructStream();
            runnable.setStream(stream);
            streamList.add(stream);
            
            executorService.execute(runnable);
            
            // We wait one second just in case, we do not want Twitter  
            // to think that we are "abusing" their Stream API by 
            // creating too many listener in too short time
            sleep(1);
        }
        
        executorService.shutdown();
        
        return streamList;
    }
    
    private void sleep(int secondsNum){        
        try{
            TimeUnit.SECONDS.sleep(secondsNum);
        } catch (InterruptedException ex){
            log.error(ex.toString(), ex);
        }
    }

    public void setCountryResolver(CountryResolver countryResolver){
        this.countryResolver = countryResolver;
    }

    public void setStatusProcessor(StatusProcessor statusProcessor){
        this.statusProcessor = statusProcessor;
    }

    public void setStreamConstructors(List<StreamConstructor> streamConstructors){
        this.streamConstructors = streamConstructors;
    }
}
