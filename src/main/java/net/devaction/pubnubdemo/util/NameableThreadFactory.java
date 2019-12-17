package net.devaction.pubnubdemo.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Víctor Gil
 *
 * since May 2019
 */
public class NameableThreadFactory implements ThreadFactory{
    private final AtomicInteger threadCount = new AtomicInteger();

    private final String namePattern;
    
    public NameableThreadFactory(String baseName){
        namePattern = baseName + "-%d";
    }
    
    @Override
    public Thread newThread(Runnable runnable){
        return new Thread(runnable, String.format(namePattern, threadCount.addAndGet(1)));
    }    
}
