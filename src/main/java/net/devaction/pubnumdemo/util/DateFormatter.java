package net.devaction.pubnumdemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class DateFormatter{
    
    public static final String DATE_FORMAT = "EEEE dd-MMM-yyyy HH:mm:ss.SSSZ";
    
    public static final SimpleDateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT,
            new Locale("en"));
    
    public static String getDateString(Date date){
        return FORMATTER.format(date);        
    }
    
    public static String getDateString(long javaTime){
        return FORMATTER.format(getDateFromJavaTime(javaTime));        
    }
    
    public static Date getDateFromJavaTime(long javaTime){
        //we received the number of milliseconds (not seconds) from 1/1/1970 as argument
        return new Date(javaTime);
    }
}


