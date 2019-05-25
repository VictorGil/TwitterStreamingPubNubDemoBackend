package net.devaction.pubnubdemo.core;

import net.devaction.pubnubdemo.util.DateFormatter;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class Tweet{
    private long id;
    private long number;
    private String country;
    private String user;
    private String text;
    private long tweetCreatedAt;    

    @Override
    public String toString(){
        return "Tweet [id=" + id + ", number=" + number + ", country=" + country + ", user=" + user + ", text=" + text
                + ", tweetCreatedAt=" + tweetCreatedAt + "(" + DateFormatter.getDateString(tweetCreatedAt) + ")]";
    }

    // Getters and setters
    public long getId(){
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public long getNumber(){
        return number;
    }
    
    public void setNumber(long number){
        this.number = number;
    }
    
    public String getCountry(){
        return country;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
    public String getUser(){
        return user;
    }
    
    public void setUser(String user){
        this.user = user;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public long getTweetCreatedAt(){
        return tweetCreatedAt;
    }
    
    public void setTweetCreatedAt(long tweetCreatedAt){
        this.tweetCreatedAt = tweetCreatedAt;
    }
}

