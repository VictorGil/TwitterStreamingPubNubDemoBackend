package net.devaction.pubnumdemo.twitter;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class StreamConstructor{
    
    // These need to be injected from the JSON config file
    private String consumerKey;
    private String consumerSecret;
    
    private String accessToken;
    private String accessTokenSecret;
    
    public TwitterStream constructStream(){
        return constructStream(consumerKey, consumerSecret, accessToken,
                accessTokenSecret);
    }
    
    private TwitterStream constructStream(String consumerKey, String consumerSecret, 
            String accessToken, String accessTokenSecret){
        
        ConfigurationBuilder configBuilder = new ConfigurationBuilder();
        configBuilder.setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);

        TwitterStreamFactory streamFactory = new TwitterStreamFactory(configBuilder.build());
        TwitterStream stream = streamFactory.getInstance();
        
        return stream;
    }

    // Setters
    public void setConsumerKey(String consumerKey){
        this.consumerKey = consumerKey;
    }
    
    public void setConsumerSecret(String consumerSecret){
        this.consumerSecret = consumerSecret;
    }
    
    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }
    
    public void setAccessTokenSecret(String accessTokenSecret){
        this.accessTokenSecret = accessTokenSecret;
    }
}

