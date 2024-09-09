package com.sk.cache.policy;

public interface EvictionPolicy<Key> {

    void keyAccessed(Key key);

    Key evictKey();

    //to determine if the get operation is considered as key access
    boolean isGetOperationConsideredAsAccessedKey();
}
