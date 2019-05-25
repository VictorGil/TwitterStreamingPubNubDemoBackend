package net.devaction.pubnubdemo.twitter;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public interface StatusProcessor{

    public void process(long id, long number, String countryName, String user, String text, 
            long tweetCreatedAt);
}

