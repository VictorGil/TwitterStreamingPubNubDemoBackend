package net.devaction.pubnubdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devaction.pubnubdemo.util.FileReader;
import net.devaction.pubnubdemo.util.JsonUnmarshaller;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class ConfigurationReader{
    private static final Logger log = LoggerFactory.getLogger(ConfigurationReader.class);
    
    private static final String CONFIG_FILENAME = "config.json";
    
    public ApplicationConfiguration read() throws Exception{
        String jsonString = new FileReader().readFileFromClasspath(CONFIG_FILENAME);
        
        ApplicationConfiguration config = JsonUnmarshaller.unmarshall(jsonString, 
                ApplicationConfiguration.class);
        
        log.info("Application configuration has been read:\n{}", config);
        return config;
    }    
}

