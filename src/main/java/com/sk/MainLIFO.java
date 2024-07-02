package com.sk;

import com.sk.cache.policy.EvictionPolicy;
import com.sk.cache.policy.LifoEvictionPolicy;
import com.sk.cache.storage.ConcurrentHashMapStorage;
import com.sk.cache.storage.Storage;
import com.sk.cache.Cache;

public class MainLIFO {
    public static void main(String[] args) {
        // Create a cache with a LIFO eviction policy and storage capacity of 3
        EvictionPolicy<String> evictionPolicy = new LifoEvictionPolicy<>();
        Storage<String, String> storage = new ConcurrentHashMapStorage<>(3);
        Cache<String, String> cache = new Cache<>(evictionPolicy, storage, false);

        // Test adding and retrieving items
        cache.put("1", "One");
        cache.put("2", "Two");
        cache.put("3", "Three");

        System.out.println("Get key '1': " + cache.get("1")); // Should print "One"
        System.out.println("Get key '2': " + cache.get("2")); // Should print "Two"

        // Add another item to trigger eviction
        cache.put("4", "Four");

        System.out.println("Get key '3': " + cache.get("3")); // Should print null if evicted
        System.out.println("Get key '4': " + cache.get("4")); // Should print "Four"

        // Adding more items to test further eviction
        cache.put("5", "Five");
        cache.put("6", "Six");

        // Check final state
        System.out.println("Get key '1': " + cache.get("1")); // Should print "One"
        System.out.println("Get key '5': " + cache.get("5")); // Should print null
        System.out.println("Get key '6': " + cache.get("6")); // Should print "Six"
        System.out.println("Get key '2': " + cache.get("2")); // Should print "Two"

    }
}

