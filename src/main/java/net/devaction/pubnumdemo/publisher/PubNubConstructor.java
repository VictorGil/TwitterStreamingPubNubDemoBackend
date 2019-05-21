package net.devaction.pubnumdemo.publisher;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class PubNubConstructor{
    
    // These need to be injected from the JSON config file
    private String publishKey;
    private String subscribeKey;    
    
    public PubNub construct(){
        return construct(publishKey, subscribeKey);
    }
    
    protected PubNub construct(String publishKey, String subscribeKey){
        PNConfiguration pnConfig = new PNConfiguration();
        pnConfig.setPublishKey(publishKey);
        pnConfig.setSubscribeKey(subscribeKey);
        pnConfig.setSecure(false);
        
        PubNub pubnub = new PubNub(pnConfig);
        return pubnub;
    }

    public void setPublishKey(String publishKey){
        this.publishKey = publishKey;
    }

    public void setSubscribeKey(String subscribeKey){
        this.subscribeKey = subscribeKey;
    }
}
