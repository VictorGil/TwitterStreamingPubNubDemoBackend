package net.devaction.pubnubdemo.core;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class CountryData{
    private Message message;
    private long lastSentTimestamp; 
    private final int maxMillis;
    private final int maxTweetNum;
        
    public CountryData(int maxMillis, int maxTweetNum){
        this.maxMillis = maxMillis;
        this.maxTweetNum = maxTweetNum;
    }

    public Message getMessage(){
        return message;
    }

    public void setMessage(Message message){
        this.message = message;
    }

    public long getLastSentTimestamp(){
        return lastSentTimestamp;
    }

    public void setLastSentTimestamp(long lastSentTimestamp){
        this.lastSentTimestamp = lastSentTimestamp;
    }

    public long getMaxMillis(){
        return maxMillis;
    }

    public long getMaxTweetNum(){
        return maxTweetNum;
    }
}
