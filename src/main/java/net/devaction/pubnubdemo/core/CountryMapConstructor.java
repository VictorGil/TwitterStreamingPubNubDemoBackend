package net.devaction.pubnubdemo.core;

import java.util.HashMap;
import java.util.Map;

import net.devaction.pubnubdemo.config.Country;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class CountryMapConstructor{

    public static Map<String,CountryData> construct(Country[] countries){
        Map<String,CountryData> map = new HashMap<String,CountryData>();
        
        for (int i = 0; i < countries.length; i++){
            Country country = countries[i];
            CountryData data = new CountryData(country.getMaxMillis(), country.getMaxTweetNum());          
            map.put(country.getName(), data);            
        }
        return map;
    }
}

