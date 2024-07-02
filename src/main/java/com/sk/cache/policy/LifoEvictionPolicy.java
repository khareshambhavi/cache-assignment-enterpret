package com.sk.cache.policy;

import java.util.concurrent.ConcurrentLinkedDeque;

public class LifoEvictionPolicy<Key> implements EvictionPolicy<Key> {
    private ConcurrentLinkedDeque<Key> stack;

    public LifoEvictionPolicy() {
        this.stack = new ConcurrentLinkedDeque<>();
    }

    @Override
    public synchronized void keyAccessed(Key key) {
        stack.remove(key);
        stack.addFirst(key);
    }

    @Override
    public synchronized Key evictKey() {
        return stack.pollFirst();
    }
}

