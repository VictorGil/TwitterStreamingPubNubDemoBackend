package net.devaction.pubnubdemo.twitter;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devaction.pubnubdemo.config.Country;
import net.devaction.pubnubdemo.twitter.TwitterStreamListenerRunnable;
import net.devaction.pubnubdemo.util.FileReader;
import net.devaction.pubnubdemo.util.JsonUnmarshaller;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class ConstructLocationsTester{
    private static final Logger log = LoggerFactory.getLogger(ConstructLocationsTester.class);
    
    public static void main(String[] args) throws Exception{
        new ConstructLocationsTester().run();
    }
    
    private void run() throws Exception{
        Country[] countries = constructCountries();
        log.info("Countries:\n{}", Arrays.toString(countries));
        
        TwitterStreamListenerRunnable streamListener = new TwitterStreamListenerRunnable();
        Country country = countries[0];
        double[][] locations = streamListener.constructLocations(country.getSouthWestLongitude(), 
                country.getSouthWestLatitude(), country.getNorthEastLongitude(),
                country.getNorthEastLatitude()); 
        
        log.info("Locations matrix:\n{}", Arrays.deepToString(locations).replace("], ", "]\n"));
    }
    
    private Country[] constructCountries() throws Exception{
        String jsonString = null;
        try{
            jsonString = new FileReader().readFileFromClasspath("countries.json");
        } catch (Exception ex){
            log.error("FATAL", ex);
            throw ex;
        }         
        Country[] countries = null;
        try{
            countries = JsonUnmarshaller.unmarshall(jsonString, Country[].class);
        } catch (Exception ex){
            log.error("FATAL", ex);
            throw ex;
        }        
        return countries;
    }    
}

