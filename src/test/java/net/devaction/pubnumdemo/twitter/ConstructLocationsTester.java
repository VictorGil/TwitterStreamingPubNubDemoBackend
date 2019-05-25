package net.devaction.pubnumdemo.twitter;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devaction.pubnumdemo.config.Country;
import net.devaction.pubnumdemo.util.FileReader;
import net.devaction.pubnumdemo.util.JsonUnmarshaller;

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
        double[][] locations = streamListener.constructLocations(countries);
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

