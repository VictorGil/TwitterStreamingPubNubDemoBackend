package net.devaction.pubnumdemo.config;

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
    
    @JsonProperty("twitter_consumer_key")
    private String twitterConsumerKey;
    
    @JsonProperty("twitter_consumer_secret")
    private String twitterConsumerSecret;
    
    @JsonProperty("twitter_access_token")
    private String twitterAccessToken;

    @JsonProperty("twitter_access_token_secret")
    private String twitterAccessTokenSecret;
    
    @JsonProperty("countries")
    private Country[] countries;

    @Override
    public String toString(){
        return "ApplicationConfiguration [blockingQueueCapacity=" + blockingQueueCapacity + 
                ", pubnumPublishKey=[not shown], pubnumSubscribeKey=[not shown]" +
                ", twitterConsumerKey=[not shown], twitterConsumerSecret=[not shown]" + 
                ", twitterAccessToken=[not shown], twitterAccessTokenSecret=[not shown]" +
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

    public String getTwitterConsumerKey(){
        return twitterConsumerKey;
    }

    public void setTwitterConsumerKey(String twitterConsumerKey){
        this.twitterConsumerKey = twitterConsumerKey;
    }

    public String getTwitterConsumerSecret(){
        return twitterConsumerSecret;
    }

    public void setTwitterConsumerSecret(String twitterConsumerSecret){
        this.twitterConsumerSecret = twitterConsumerSecret;
    }

    public String getTwitterAccessToken(){
        return twitterAccessToken;
    }

    public void setTwitterAccessToken(String twitterAccessToken){
        this.twitterAccessToken = twitterAccessToken;
    }

    public String getTwitterAccessTokenSecret(){
        return twitterAccessTokenSecret;
    }

    public void setTwitterAccessTokenSecret(String twitterAccessTokenSecret){
        this.twitterAccessTokenSecret = twitterAccessTokenSecret;
    }

    public Country[] getCountries(){
        return countries;
    }

    public void setCountries(Country[] countries){
        this.countries = countries;
    }    
}

