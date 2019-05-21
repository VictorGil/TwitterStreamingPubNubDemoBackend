package net.devaction.pubnumdemo.core;

import java.util.Date;
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
    
    private Message message;    
   
    private long lastSentTimestamp;
    
    // It needs to be injected
    private long maxMillis;
    
    // It needs to be injected
    private long maxTweetNum;
    
    public TweetProcessorImpl(BlockingQueue<Message> queue){
        this.queue = queue;
    }    
    
    @Override
    public void process(Tweet tweet){
        if (message == null)
            message = new Message();
        
        message.add(tweet);
        if (message.size() == maxTweetNum || timeToSendIt()){
            if (queue.offer(message)){
                log.trace("{} containing {} tweets has been added to the queue.", Message.class.getSimpleName(), 
                        message.size());
                message = null;
                lastSentTimestamp = new Date().getTime();
            } else{
                log.error("Unable to add " + Message.class.getSimpleName() + " to the queue, discarding it");
            }
        } 
    }

    protected boolean timeToSendIt(){
        return new Date().getTime() - lastSentTimestamp >= maxMillis;
    }

    public void setMaxMillis(long maxMillis){
        this.maxMillis = maxMillis;
    }

    public void setMaxTweetNum(long maxMessageNum){
        this.maxTweetNum = maxMessageNum;
    }
}

