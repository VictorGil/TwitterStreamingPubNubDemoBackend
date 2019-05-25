package net.devaction.pubnumdemo.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devaction.pubnumdemo.config.Country;
import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class TwitterStreamListenerRunnable implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(TwitterStreamListenerRunnable.class);

    // It needs to be injected
    private TwitterStream stream;
    
    private Country country;
    
    // It needs to be injected
    private StatusListener listener;
    
    public TwitterStreamListenerRunnable(Country country){
        this.country = country;
    }

    // To be used just when testing
    protected TwitterStreamListenerRunnable(){        
    }
    
    @Override
    public void run(){
        stream.addListener(listener); 
        
        FilterQuery filter = contructFilter(country);
        stream.filter(filter);
       
        log.info("Starting {} for {}", this.getClass().getSimpleName(), country.getName());
        stream.sample();
        
        log.info("{} for {} finished", this.getClass().getSimpleName(), country.getName());
    }

    protected FilterQuery contructFilter(Country country){

        double[][] locations = constructLocations(country.getSouthWestLongitude(), 
                country.getSouthWestLatitude(), country.getNorthEastLongitude(),
                country.getNorthEastLatitude()); 
                
        FilterQuery filterQuery = new FilterQuery();        
        filterQuery.locations(locations); 
        
        return filterQuery;    
    }
    
    protected double[][] constructLocations(double southWestLongitude, double southWestLatitude, 
            double northEastLongitude, double northEastLatitude){
        
        double[][] locations = new double[2][2];
        locations[0] = new double[2];
        
        locations[0][0] = southWestLongitude;
        locations[0][1] = southWestLatitude;
        locations[1][0] = northEastLongitude;
        locations[1][1] = northEastLatitude;
        
        return locations;
    }
    
    public void setStream(TwitterStream stream){
        this.stream = stream;
    }

    public void setListener(StatusListener listener){
        this.listener = listener;
    }
}
