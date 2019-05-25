package net.devaction.pubnumdemo.twitter;

import net.devaction.pubnumdemo.core.Tweet;
import net.devaction.pubnumdemo.core.TweetProcessor;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class StatusProcessorImpl implements StatusProcessor{
    
    // These two need to be injected
    private TweetProcessor tweetProcessor;
    private CountryResolver countryResolver;    
    
    @Override
    public void process(long id, long number, String user, String text, long tweetCreatedAt, double longitude, double latitude){
        String countryName = null;
        try{
            countryName = countryResolver.resolve(longitude, latitude);
        } catch (NoCountryException e){
            return;
        }
        
        process(id, number, countryName, user, text, tweetCreatedAt);
    }
    
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

    public void setCountryResolver(CountryResolver countryResolver){
        this.countryResolver = countryResolver;
    }
}

