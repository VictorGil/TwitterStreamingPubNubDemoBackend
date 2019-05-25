package net.devaction.pubnubdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class JsonUnmarshaller{
    
    private static final Logger log = LoggerFactory.getLogger(JsonUnmarshaller.class);

    public static <T> T unmarshall(String jsonString, Class<T> clazz) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        T object = null;
        try{ 
            object = objectMapper.readValue(jsonString, clazz);
        } catch(Exception ex){
            String errorMessage = "Error when trying to unmarshall:\n" + jsonString + 
                    " into a " + clazz.getName() + " object.";
            log.error(errorMessage, ex);
            throw new Exception(errorMessage, ex);
        }
        
        log.trace("Unmarshalled/deserialized object from JSON String: {}", object);    
        
        return object;
    }
}
