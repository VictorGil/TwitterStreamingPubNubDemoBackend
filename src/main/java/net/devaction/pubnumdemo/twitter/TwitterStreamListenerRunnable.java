package net.devaction.pubnumdemo.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    // They needs to be injected
    private double southWestLongitude;
    private double southWestLatitude;
    private double northEastLongitude;
    private double northEastLatitude;
    
    private final String country;
    
    // It needs to be injected
    private StatusListener listener;
    
    public TwitterStreamListenerRunnable(String country){
        this.country = country;
    }
    
    @Override
    public void run(){
        stream.addListener(listener); 
        
        FilterQuery filter = contructFilter(southWestLongitude, southWestLatitude, 
                northEastLongitude, northEastLatitude);
        stream.filter(filter);
       
        log.info("Starting {} {}", country, this.getClass().getSimpleName());
        stream.sample();        
    }
    
    protected FilterQuery contructFilter(double southWestLongitude, double southWestLatitude, 
            double northEastLongitude, double northEastLatitude){
        
        FilterQuery filterQuery = new FilterQuery();
        
        filterQuery.locations(new double[][]{
                new double[]{southWestLongitude, southWestLatitude}, 
                new double[]{northEastLongitude, northEastLatitude}});
        
        return filterQuery;    
    }

    public void setStream(TwitterStream stream){
        this.stream = stream;
    }

    public void setSouthWestLongitude(double southWestLongitude){
        this.southWestLongitude = southWestLongitude;
    }

    public void setSouthWestLatitude(double southWestLatitude){
        this.southWestLatitude = southWestLatitude;
    }

    public void setNorthEastLongitude(double northEastLongitude){
        this.northEastLongitude = northEastLongitude;
    }

    public void setNorthEastLatitude(double northEastLatitude){
        this.northEastLatitude = northEastLatitude;
    }

    public StatusListener getListener(){
        return listener;
    }

    public void setListener(StatusListener listener){
        this.listener = listener;
    }
}
