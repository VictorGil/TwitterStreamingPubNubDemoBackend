package net.devaction.pubnumdemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class FileReader{
    private static final Logger log = LoggerFactory.getLogger(FileReader.class);
    private static final String CHARSET_NAME = "UTF-8";
    
    public String readFileFromClasspath(String filename) throws Exception{
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(filename);
        
        String fileContent = null;
        BufferedReader reader = null;
        try{            
            reader = new BufferedReader(new InputStreamReader(inputStream, CHARSET_NAME));
            fileContent = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch(UnsupportedEncodingException ex){
            log.error("{} charset is not supported", CHARSET_NAME, ex);
            throw ex;
        } catch(UncheckedIOException ex){
            log.error("Error when trying to read from file: {}", filename, ex);
            throw ex;
        } finally{
            if (reader != null) {
                try{
                    reader.close();
                } catch (IOException ex){
                    log.error("Exception when closing the BufferedReader", ex);
                }
            }
        }
        
        return fileContent;        
    }
}
