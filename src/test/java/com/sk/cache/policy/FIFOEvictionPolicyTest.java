package com.sk.cache.policy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FIFOEvictionPolicyTest {

    private FifoEvictionPolicy<Integer> fifoEvictionPolicy;

    @BeforeEach
    void setUp() {
        fifoEvictionPolicy = new FifoEvictionPolicy<>();
    }

    @Test
    void testNoKeyToEvictInitially() {
        assertNull(fifoEvictionPolicy.evictKey());
    }

    @Test
    void testKeysAreEvictedInFirstInFirstOutOrder() {
        fifoEvictionPolicy.keyAccessed(1);
        fifoEvictionPolicy.keyAccessed(2);
        fifoEvictionPolicy.keyAccessed(3);
        fifoEvictionPolicy.keyAccessed(4);
        
        assertEquals(1, fifoEvictionPolicy.evictKey());
        assertEquals(2, fifoEvictionPolicy.evictKey());
        assertEquals(3, fifoEvictionPolicy.evictKey());
        assertEquals(4, fifoEvictionPolicy.evictKey());
    }

    @Test
    void testReaccessingKeyPreventsItFromEviction() {
        fifoEvictionPolicy.keyAccessed(1);
        fifoEvictionPolicy.keyAccessed(2);
        fifoEvictionPolicy.keyAccessed(3);
        fifoEvictionPolicy.keyAccessed(2);
        fifoEvictionPolicy.keyAccessed(4);
        fifoEvictionPolicy.keyAccessed(1);
        fifoEvictionPolicy.keyAccessed(5);
        
        assertEquals(3, fifoEvictionPolicy.evictKey());
        assertEquals(2, fifoEvictionPolicy.evictKey());
        assertEquals(4, fifoEvictionPolicy.evictKey());
        assertEquals(1, fifoEvictionPolicy.evictKey());
        assertEquals(5, fifoEvictionPolicy.evictKey());
    }
}

