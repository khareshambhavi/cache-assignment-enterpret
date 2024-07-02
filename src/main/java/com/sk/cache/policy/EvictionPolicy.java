package com.sk.cache.policy;

/**
 * An interface for eviction policies.
 *
 * @param <Key> Type of key.
 */
public interface EvictionPolicy<Key> {

    void keyAccessed(Key key);

    Key evictKey();
}
