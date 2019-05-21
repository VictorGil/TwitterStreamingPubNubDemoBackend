package net.devaction.pubnumdemo.core;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class Message{
    private List<Tweet> tweets = new LinkedList<Tweet>();
    
    public void add(Tweet tweet){
        tweets.add(tweet);
    }

    public int size(){
        return tweets.size();
    }
    @Override
    public String toString(){
        return "Message [tweets=" + tweets + "]";
    }

    public List<Tweet> getTweets(){
        return tweets;
    }

    public void setTweets(List<Tweet> tweets){
        this.tweets = tweets;
    }
    
    public String getCountry(){
        return tweets.get(0).getCountry();
    }
}

