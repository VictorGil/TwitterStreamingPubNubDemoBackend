package net.devaction.pubnumdemo.main;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devaction.pubnumdemo.config.Country;
import net.devaction.pubnumdemo.twitter.StreamConstructor;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class StreamConstructorsConstructor{
    private static final Logger log = LoggerFactory.getLogger(StreamConstructorsConstructor.class);
    
    protected List<StreamConstructor> construct(Country[] countries){
        List<StreamConstructor> constructors = new LinkedList<StreamConstructor>();
        for (int i = 0; i < countries.length; i++){
            Country country = countries[i];
            
            StreamConstructor streamConstructor = new StreamConstructor();
            streamConstructor.setAccessToken(country.getTwitterAccessToken());
            streamConstructor.setAccessTokenSecret(country.getTwitterAccessTokenSecret());
            streamConstructor.setConsumerKey(country.getTwitterConsumerKey());
            streamConstructor.setConsumerSecret(country.getTwitterConsumerSecret());
            
            constructors.add(streamConstructor);
        }
        
        log.debug("All the {} objects have been created.", StreamConstructor.class.getSimpleName());
        return constructors;
    }
}

