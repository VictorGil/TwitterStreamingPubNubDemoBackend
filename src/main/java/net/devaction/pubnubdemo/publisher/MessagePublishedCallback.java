package net.devaction.pubnubdemo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class MessagePublishedCallback extends PNCallback<PNPublishResult>{
    private static final Logger log = LoggerFactory.getLogger(MessagePublishedCallback.class);

    @Override
    public void onResponse(PNPublishResult result, PNStatus status){
        if (!status.isError()) {
            log.trace("Message successfully published");
        } else {
            log.error("Unable to publish the message to PubNub: {}", status.getCategory()); 
            log.info("Error data: {}\n", status.getErrorData(), status.getErrorData().getThrowable());
        }        
    }
}
