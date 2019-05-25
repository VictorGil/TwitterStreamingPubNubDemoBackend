package net.devaction.pubnubdemo.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class Country{
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("southWest_Longitude")
    private double southWestLongitude;

    @JsonProperty("southWest_Latitude")
    private double southWestLatitude;
    
    @JsonProperty("northEast_Longitude")
    private double northEastLongitude;

    @JsonProperty("northEast_Latitude")
    private double northEastLatitude;

    // Maximum number of tweets in a PubNum message
    @JsonProperty("max_tweet_num")
    private int maxTweetNum; 
    
    // Maximum milliseconds between sent PubNum messages
    @JsonProperty("max_millis")
    private int maxMillis;    

    @JsonProperty("twitter_consumer_key")
    private String twitterConsumerKey;
    
    @JsonProperty("twitter_consumer_secret")
    private String twitterConsumerSecret;
    
    @JsonProperty("twitter_access_token")
    private String twitterAccessToken;

    @JsonProperty("twitter_access_token_secret")
    private String twitterAccessTokenSecret;
    
    @Override
    public String toString(){
        return "Country [name=" + name + ", southWestLongitude=" + southWestLongitude + ", southWestLatitude=" + southWestLatitude
                + ", northEastLongitude=" + northEastLongitude + ", northEastLatitude=" + northEastLatitude + ", maxTweetNum=" + maxTweetNum
                + ", maxMillis=" + maxMillis + "]";
    }

    // Getters and setters
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getSouthWestLongitude(){
        return southWestLongitude;
    }

    public void setSouthWestLongitude(double southWestLongitude){
        this.southWestLongitude = southWestLongitude;
    }

    public double getSouthWestLatitude(){
        return southWestLatitude;
    }

    public void setSouthWestLatitude(double southWestLatitude){
        this.southWestLatitude = southWestLatitude;
    }

    public double getNorthEastLongitude(){
        return northEastLongitude;
    }

    public void setNorthEastLongitude(double northEastLongitude){
        this.northEastLongitude = northEastLongitude;
    }

    public double getNorthEastLatitude(){
        return northEastLatitude;
    }

    public void setNorthEastLatitude(double northEastLatitude){
        this.northEastLatitude = northEastLatitude;
    }

    public int getMaxTweetNum(){
        return maxTweetNum;
    }

    public void setMaxTweetNum(int maxTweetNum){
        this.maxTweetNum = maxTweetNum;
    }

    public int getMaxMillis(){
        return maxMillis;
    }

    public void setMaxMillis(int maxMillis){
        this.maxMillis = maxMillis;
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

    public String getTwitterAccessTokenSecret(){
        return twitterAccessTokenSecret;
    }

    public void setTwitterAccessTokenSecret(String twitterAccessTokenSecret){
        this.twitterAccessTokenSecret = twitterAccessTokenSecret;
    }

    public void setTwitterAccessToken(String twitterAccessToken){
        this.twitterAccessToken = twitterAccessToken;
    }

    public String getTwitterAccessToken(){
        return twitterAccessToken;
    }
}

