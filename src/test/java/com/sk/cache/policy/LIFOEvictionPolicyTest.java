
package com.sk.cache.policy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LIFOEvictionPolicyTest {

    private LifoEvictionPolicy<Integer> lifoEvictionPolicy;

    @BeforeEach
    void setUp() {
        lifoEvictionPolicy = new LifoEvictionPolicy<>();
    }

    @Test
    void testNoKeyToEvictInitially() {
        assertNull(lifoEvictionPolicy.evictKey());
    }

    @Test
    void testKeysAreEvictedInLastInFirstOutOrder() {
        lifoEvictionPolicy.keyAccessed(1);
        lifoEvictionPolicy.keyAccessed(2);
        lifoEvictionPolicy.keyAccessed(3);
        lifoEvictionPolicy.keyAccessed(4);
        
        assertEquals(4, lifoEvictionPolicy.evictKey());
        assertEquals(3, lifoEvictionPolicy.evictKey());
        assertEquals(2, lifoEvictionPolicy.evictKey());
        assertEquals(1, lifoEvictionPolicy.evictKey());
    }

    @Test
    void testReaccessingKeyMakesItEvictEarlier() {
        lifoEvictionPolicy.keyAccessed(1);
        lifoEvictionPolicy.keyAccessed(2);
        lifoEvictionPolicy.keyAccessed(3);
        lifoEvictionPolicy.keyAccessed(2);
        lifoEvictionPolicy.keyAccessed(4);
        lifoEvictionPolicy.keyAccessed(1);
        lifoEvictionPolicy.keyAccessed(5);
        
        assertEquals(5, lifoEvictionPolicy.evictKey());
        assertEquals(1, lifoEvictionPolicy.evictKey());
        assertEquals(4, lifoEvictionPolicy.evictKey());
        assertEquals(2, lifoEvictionPolicy.evictKey());
        assertEquals(3, lifoEvictionPolicy.evictKey());
    }
}

