package net.devaction.pubnubdemo.twitter;

import net.devaction.pubnubdemo.core.Tweet;
import net.devaction.pubnubdemo.core.TweetProcessor;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class StatusProcessorImpl implements StatusProcessor{
    
    // It needs to be injected
    private TweetProcessor tweetProcessor;
    
    @Override
    public void process(long id, long number, String countryName, String user, String text, 
            long tweetCreatedAt){
        Tweet tweet = createTweet(id, number, countryName, user, text, tweetCreatedAt);
        tweetProcessor.process(tweet);         
    }
    
    protected Tweet createTweet(long id, long number, String country, String user, String text, 
            long tweetCreatedAt){

        Tweet tweet = new Tweet();
        tweet.setId(id);
        tweet.setNumber(number);
        tweet.setCountry(country);
        tweet.setUser(user);
        tweet.setText(text);
        tweet.setTweetCreatedAt(tweetCreatedAt);
        
        return tweet;
    }

    public void setTweetProcessor(TweetProcessor tweetProcessor){
        this.tweetProcessor = tweetProcessor;
    }
}

