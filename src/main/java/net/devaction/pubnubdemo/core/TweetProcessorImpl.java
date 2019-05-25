package net.devaction.pubnubdemo.core;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class TweetProcessorImpl implements TweetProcessor{
    private static final Logger log = LoggerFactory.getLogger(TweetProcessorImpl.class);

    private final BlockingQueue<Message> queue;
    
    // This needs to be injected
    private Map<String,CountryData> countryMap;
    
    public TweetProcessorImpl(BlockingQueue<Message> queue){
        this.queue = queue;
    }    
    
    @Override
    public void process(Tweet tweet){
        
        if (countryMap.get(tweet.getCountry()).getMessage() == null)
            countryMap.get(tweet.getCountry()).setMessage(new Message());
        
        countryMap.get(tweet.getCountry()).getMessage().add(tweet);
        
        if (countryMap.get(tweet.getCountry()).getMessage().size() == countryMap.get(tweet.getCountry()).getMaxTweetNum() 
                || timeToSendIt(countryMap.get(tweet.getCountry()).getLastSentTimestamp(), 
                countryMap.get(tweet.getCountry()).getMaxMillis())){
            
            if (queue.offer(countryMap.get(tweet.getCountry()).getMessage())){
                log.trace("{} containing {} tweets has been added to the queue.", Message.class.getSimpleName(), 
                        countryMap.get(tweet.getCountry()).getMessage().size());
                countryMap.get(tweet.getCountry()).setMessage(null);
                countryMap.get(tweet.getCountry()).setLastSentTimestamp(new Date().getTime());
            } else{
                log.error("Unable to add " + Message.class.getSimpleName() + " to the queue, discarding it");
            }
        } 
    }

    protected boolean timeToSendIt(long lastSentTimestamp, long maxMillis){
        return new Date().getTime() - lastSentTimestamp >= maxMillis;
    }

    public void setCountryMap(Map<String, CountryData> countryMap){
        this.countryMap = countryMap;
    }
}

