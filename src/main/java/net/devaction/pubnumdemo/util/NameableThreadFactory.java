package net.devaction.pubnumdemo.util;

import java.util.concurrent.ThreadFactory;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class NameableThreadFactory implements ThreadFactory{
    private int threadsNum;
    private final String namePattern;
    
    public NameableThreadFactory(String baseName){
        namePattern = baseName + "-%d";
    }
    
    @Override
    public Thread newThread(Runnable runnable){
        threadsNum++;
        return new Thread(runnable, String.format(namePattern, threadsNum));
    }    
}

