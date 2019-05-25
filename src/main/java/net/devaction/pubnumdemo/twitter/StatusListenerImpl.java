package net.devaction.pubnumdemo.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devaction.pubnumdemo.util.DateFormatter;
import twitter4j.GeoLocation;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class StatusListenerImpl implements StatusListener{
    private static final Logger log = LoggerFactory.getLogger(StatusListenerImpl.class);
    
    private long tweetsNum;
    
    // It needs to be injected
    private StatusProcessor statusProcessor; 
    
    @Override
    public void onException(Exception ex){
        log.error(ex.toString(), ex);        
    }

    @Override
    public void onStatus(Status status){
        GeoLocation location = status.getGeoLocation();
        
        if (location == null){
            log.warn("Assertion error: GeoLocation is null for this tweet, id: {}", status.getId());
            statusProcessor.process(status.getId(), tweetsNum, status.getUser().getName(), 
                    status.getText(), status.getCreatedAt().getTime(), -1.1303, 37.9861);
            return;
        }
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        
        log.trace("New Tweet received:\nUser: {}\nId: {}\nText: {}\nCreated at: {}\n" + 
                "Longitude: {}\nLatitude: {}", status.getUser().getName(), status.getId(), 
                status.getText(), DateFormatter.getDateString(status.getCreatedAt()), 
                longitude, latitude);
        
        log.info("Number of tweets received so far: {}", ++tweetsNum);
        
        statusProcessor.process(status.getId(), tweetsNum, status.getUser().getName(), 
                status.getText(), status.getCreatedAt().getTime(), longitude, latitude);
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice){
        //log.trace(statusDeletionNotice.toString());        
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses){
        log.debug("Track limitation notice: {}", numberOfLimitedStatuses);
        
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId){
        log.debug("ScrubGeo: user id: {}, up to status id: {}", userId, upToStatusId);
        
    }

    @Override
    public void onStallWarning(StallWarning warning){
        log.warn("Stall warning: {}", warning.toString());        
    }

    public void setStatusProcessor(StatusProcessor statusProcessor){
        this.statusProcessor = statusProcessor;
    }
}

