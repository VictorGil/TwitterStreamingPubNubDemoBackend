package net.devaction.pubnubdemo.twitter;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devaction.pubnubdemo.config.Country;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class CountryResolver{
    private static final Logger log = LoggerFactory.getLogger(CountryResolver.class);
    
    private final Country[] countries;
    
    public CountryResolver(Country[] countries){
        this.countries = countries;
    }
    
    protected String resolve(double longitude, double latitude) throws NoCountryException{
        for (int i = 0; i < countries.length; i++){
            Country country = countries[i];
            if (longitude >= country.getSouthWestLongitude() && 
                    longitude <= country.getNorthEastLongitude() &&
                    latitude >= country.getSouthWestLatitude() &&
                    latitude <= country.getNorthEastLatitude())
                return country.getName();
        }
        
        log.error("Unable to find a country for longitude {}, latitude: {}, countries: {}", 
                longitude, latitude, Arrays.toString(countries));
        
        throw new NoCountryException();
    }
}
