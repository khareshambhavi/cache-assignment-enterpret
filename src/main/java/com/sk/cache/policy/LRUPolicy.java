package com.sk.cache.policy;

import com.sk.dsa.LinkedList;
import com.sk.dsa.LinkedListNode;

import java.security.Key;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class LRUPolicy<Key> implements EvictionPolicy<Key> {

    private LinkedList<Key> dll;
    private Map<Key, LinkedListNode<Key>> mapper;

    public LRUPolicy() {
        this.dll = new LinkedList<>();
        this.mapper = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            LinkedListNode<Key> node = mapper.get(key);
            synchronized (this) {
                dll.detachNode(node);
                dll.addNodeAtLast(node);
            }
        }
        else {
            LinkedListNode<Key> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public synchronized Key evictKey() {
        LinkedListNode<Key> first = dll.getFirstNode();
        if(first == null) {
            return null;
        }
        synchronized(this) {
            dll.detachNode(first);
        }
        return first.getElement();
    }

    @Override
    public boolean isGetOperationConsideredAsAccessedKey() {
        return true; // LRU considers get operation as key access
    }
}
