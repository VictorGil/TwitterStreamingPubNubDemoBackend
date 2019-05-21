package net.devaction.pubnumdemo.twitter;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public interface StatusProcessor{

    public void process(long id, long number, String country, String user, String text, 
            long tweetCreatedAt);
}

