package net.devaction.pubnubdemo.config;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class ApplicationConfiguration{

    @JsonProperty("blocking_queue_capacity")
    private int blockingQueueCapacity;
    
    @JsonProperty("pubnub_publish_key")
    private String pubnubPublishKey;
        
    @JsonProperty("pubnub_subscribe_key")
    private String pubnubSubscribeKey;    
   
    @JsonProperty("countries")
    private Country[] countries;

    @Override
    public String toString(){
        return "ApplicationConfiguration [blockingQueueCapacity=" + blockingQueueCapacity + 
                ", pubnubPublishKey=[not shown], pubnubSubscribeKey=[not shown]" +
                ", countries=" + Arrays.toString(countries) + "]";
    }

    // Getters and setters
    public int getBlockingQueueCapacity(){
        return blockingQueueCapacity;
    }

    public void setBlockingQueueCapacity(int blockingQueueCapacity){
        this.blockingQueueCapacity = blockingQueueCapacity;
    }

    public String getPubnubPublishKey(){
        return pubnubPublishKey;
    }

    public void setPubnubPublishKey(String pubnubPublishKey){
        this.pubnubPublishKey = pubnubPublishKey;
    }

    public String getPubnubSubscribeKey(){
        return pubnubSubscribeKey;
    }

    public void setPubnubSubscribeKey(String pubnubSubscribeKey){
        this.pubnubSubscribeKey = pubnubSubscribeKey;
    }

    public Country[] getCountries(){
        return countries;
    }

    public void setCountries(Country[] countries){
        this.countries = countries;
    }    
}

