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
    private String countryName;
    
    // It needs to be injected
    private StatusProcessor statusProcessor; 
    
    // It needs to be injected
    private CountryResolver countryResolver;
    
    @Override
    public void onException(Exception ex){
        log.error(ex.toString(), ex);        
    }

    @Override
    public void onStatus(Status status){
        GeoLocation geoLocation = status.getGeoLocation();
        
        if (geoLocation != null){
            log.debug("New Tweet with geo-location data received:\nUser: {}\nId: {}\nText: {}\nCreated at: {}\n" + 
                    "Longitude: {}\nLatitude: {}", status.getUser().getName(), status.getId(), 
                    status.getText(), DateFormatter.getDateString(status.getCreatedAt()), 
                    geoLocation.getLongitude(), geoLocation.getLatitude());
            
            String countryName = null;
            try{
                countryName = countryResolver.resolve(geoLocation.getLongitude(), geoLocation.getLatitude());
            } catch (NoCountryException ex){
                log.error("The geo-location does not match any of the countries.");
            }
            if (countryName != null && !this.countryName.equals(countryName)) {
                log.error("The country based on geoLocation does not match the country based"
                        + " on IP location for this tweet, id: {}, IP location country: {}, "
                        + "Geo/GPS location country: {}", status.getId(), this.countryName, countryName);
            }
            
        }
        
        log.trace("New Tweet received:\nUser: {}\nId: {}\nText: {}\nCreated at: {}\nCountry: {}", 
                status.getUser().getName(), status.getId(), status.getText(), 
                DateFormatter.getDateString(status.getCreatedAt()), countryName);
        
        log.debug("Number of tweets received so far: {}", ++tweetsNum);
        
        statusProcessor.process(status.getId(), tweetsNum, this.countryName, status.getUser().getName(), 
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

    public void setCountryName(String countryName){
        this.countryName = countryName;
    }

    public void setCountryResolver(CountryResolver countryResolver){
        this.countryResolver = countryResolver;
    }
}

