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
    
    @JsonProperty("pubnum_publish_key")
    private String pubnumPublishKey;
        
    @JsonProperty("pubnum_subscribe_key")
    private String pubnumSubscribeKey;    
   
    @JsonProperty("countries")
    private Country[] countries;

    @Override
    public String toString(){
        return "ApplicationConfiguration [blockingQueueCapacity=" + blockingQueueCapacity + 
                ", pubnumPublishKey=[not shown], pubnumSubscribeKey=[not shown]" +
                ", countries=" + Arrays.toString(countries) + "]";
    }

    // Getters and setters
    public int getBlockingQueueCapacity(){
        return blockingQueueCapacity;
    }

    public void setBlockingQueueCapacity(int blockingQueueCapacity){
        this.blockingQueueCapacity = blockingQueueCapacity;
    }

    public String getPubnumPublishKey(){
        return pubnumPublishKey;
    }

    public void setPubnumPublishKey(String pubnumPublishKey){
        this.pubnumPublishKey = pubnumPublishKey;
    }

    public String getPubnumSubscribeKey(){
        return pubnumSubscribeKey;
    }

    public void setPubnumSubscribeKey(String pubnumSubscribeKey){
        this.pubnumSubscribeKey = pubnumSubscribeKey;
    }

    public Country[] getCountries(){
        return countries;
    }

    public void setCountries(Country[] countries){
        this.countries = countries;
    }    
}

