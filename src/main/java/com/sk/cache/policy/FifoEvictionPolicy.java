package com.sk.cache.policy;

import java.security.Key;
import java.util.concurrent.ConcurrentLinkedDeque;

public class FifoEvictionPolicy<Key> implements EvictionPolicy<Key> {
    private ConcurrentLinkedDeque<Key> queue;

    public FifoEvictionPolicy() {
        this.queue = new ConcurrentLinkedDeque<>();
    }

    @Override
    public synchronized void keyAccessed(Key key) {
        queue.remove(key);
        queue.addLast(key);
    }

    @Override
    public synchronized Key evictKey() {
        return queue.pollFirst();
    }

    @Override
    public boolean isGetOperationConsideredAsAccessedKey() {
        return false; 
    }
}
