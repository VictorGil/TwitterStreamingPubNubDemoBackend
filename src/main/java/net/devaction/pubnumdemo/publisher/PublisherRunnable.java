package net.devaction.pubnumdemo.publisher;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.endpoints.pubsub.Publish;
import com.pubnub.api.models.consumer.PNPublishResult;

import net.devaction.pubnumdemo.core.Message;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class PublisherRunnable implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(PublisherRunnable.class);
    
    // It needs to be injected
    private Publish publish;
    
    private final BlockingQueue<Message> queue;
    
    public PublisherRunnable(BlockingQueue<Message> queue){
        this.queue = queue;
    }
    
    // It needs to be injected
    private PNCallback<PNPublishResult> callback;
    
    @Override
    public void run(){
        Message message = null;
        while (true){
            try{
                message = queue.take();
            } catch (InterruptedException ex){
                log.error(ex.toString(), ex);
                return;
            }
            log.trace("Going to publish a message. Message queue size: {}, message:\n{}", 
                    queue.size(), message);
            publish.message(message).channel(message.getCountry()).async(callback);
        }        
    }

    public void setPublish(Publish publish){
        this.publish = publish;
    }

    public void setCallback(PNCallback<PNPublishResult> callback){
        this.callback = callback;
    }
}
