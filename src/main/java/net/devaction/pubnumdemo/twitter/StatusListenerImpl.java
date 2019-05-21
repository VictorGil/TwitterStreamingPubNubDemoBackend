package net.devaction.pubnumdemo.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devaction.pubnumdemo.util.DateFormatter;
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
    
    private final String country;
    
    // It needs to be injected
    private StatusProcessor statusProcessor; 
    
    public StatusListenerImpl(String country){
        this.country = country;
    }
    
    @Override
    public void onException(Exception ex){
        log.error(ex.toString(), ex);        
    }

    @Override
    public void onStatus(Status status){
        log.trace("New Tweet received:\nCountry: {}\nUser: {}\nId: {}\nText: {}\n Created at: {}", 
                country, status.getUser().getName(),
                status.getId(), status.getText(), DateFormatter.getDateString(status.getCreatedAt()));
        log.info("Number of tweets received from {} so far: {}", country, ++tweetsNum);
        
        statusProcessor.process(status.getId(), tweetsNum, country, status.getUser().getName(), 
                status.getText(), status.getCreatedAt().getTime());
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

