package com.sk.cache;

import com.sk.cache.exceptions.NotFoundException;
import com.sk.cache.exceptions.StorageFullException;
import com.sk.cache.policy.EvictionPolicy;
import com.sk.cache.storage.Storage;

public class Cache<Key, Value> {
    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void put(Key key, Value value) {
        try {
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException exception) {
            System.out.println("Storage is full. Trying to remove.");
            Key keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("Unexpected State. Storage full and no key to evict.");
            }
            this.storage.remove(keyToRemove);
            System.out.println("Created space by evicting item " + keyToRemove);
            put(key, value);
        }
    }

    public Value get(Key key) {
        try {
            Value value = this.storage.get(key);
            if (evictionPolicy.isGetOperationConsideredAsAccessedKey()) {
                this.evictionPolicy.keyAccessed(key);
            }
            return value;
        } catch (NotFoundException notFoundException) {
            System.out.println("Tried to get a non-existing key.");
            return null;
        }
    }
}
