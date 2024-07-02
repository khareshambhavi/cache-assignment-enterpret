package com.sk.cache.storage;

import com.sk.cache.exceptions.NotFoundException;
import com.sk.cache.exceptions.StorageFullException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapStorage<Key, Value> implements Storage<Key, Value> {

    private final Map<Key, Value> storage;
    private final Integer capacity;

    public ConcurrentHashMapStorage(Integer capacity) {
        this.capacity = capacity;
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public void add(Key key, Value value) throws StorageFullException {
        if (isStorageFull()) throw new StorageFullException("Storage is full");
        storage.put(key, value);
    }

    @Override
    public void remove(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + " does not exist in cache.");
        storage.remove(key);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + " does not exist in cache.");
        return storage.get(key);
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}