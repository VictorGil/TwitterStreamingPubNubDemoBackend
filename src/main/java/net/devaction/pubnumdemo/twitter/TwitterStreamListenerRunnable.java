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
    
    private Country[] countries;
    
    // It needs to be injected
    private StatusListener listener;
    
    public TwitterStreamListenerRunnable(Country[] countries){
        this.countries = countries;
    }

    // To be used just when testing
    protected TwitterStreamListenerRunnable(){        
    }
    
    @Override
    public void run(){
        stream.addListener(listener); 
        
        FilterQuery filter = contructFilter(countries);
        stream.filter(filter);
       
        log.info("Starting {}", this.getClass().getSimpleName());
        stream.sample();        
    }

    protected FilterQuery contructFilter(Country[] countries){

        double[][] locations = constructLocations(countries); 
                
        FilterQuery filterQuery = new FilterQuery();        
        filterQuery.locations(locations); 
        
        return filterQuery;    
    }
    
    protected double[][] constructLocations(Country[] countries){
        double[][] locations = new double[countries.length * 2][2];
        
        int j = 0;
        for (int i = 0; i < countries.length; i++){
            locations[j] = new double[2];
            locations[j][0] = countries[i].getSouthWestLongitude();
            locations[j][1] = countries[i].getSouthWestLatitude();
            locations[j + 1][0] = countries[i].getNorthEastLongitude();
            locations[j + 1][1] = countries[i].getNorthEastLatitude();
            j = j + 2;
        }        
        
        return locations;
    }
    
    public void setStream(TwitterStream stream){
        this.stream = stream;
    }

    public StatusListener getListener(){
        return listener;
    }

    public void setListener(StatusListener listener){
        this.listener = listener;
    }
}
