# In Memory Cache Library

The In Memory Cache Library is a Java library designed for efficient, thread-safe, and extensible in-memory caching. It supports various eviction policies such as LRU (Least Recently Used), LIFO (Last In First Out), and FIFO (First In First Out), and allows for easy integration of custom eviction policies through dependency injection.
I made this project as part of an assignment for Backend Internship at Enterpret.

## Features

- **Thread Safety**: Utilizes `ConcurrentHashMap` and synchronized blocks/methods to ensure thread safety, making it suitable for concurrent access scenarios.

- **Support for Eviction Policies**:
  - **LRU (Least Recently Used)**: Evicts the least recently used items first.
  - **LIFO (Last In First Out)**: Evicts the last added items first.
  - **FIFO (First In First Out)**: Evicts the oldest added items first.
  - **Custom Eviction Policies**: Extensible architecture allows integration of custom eviction policies via dependency injection.

- **Modular and Readable Code**: Follows Object-Oriented Programming (OOP) principles with a modular and readable codebase, enhancing maintainability and ease of extension.

- **Unit Tests**: Includes comprehensive unit tests to validate functionality and ensure robustness.

## Getting Started

To use the In Memory Cache Library in your Java project, follow these steps:

1. **Integration**: Include the library in your project dependencies or download the JAR file.

2. **Configuration**: Create instances of the cache using preferred eviction policies.

   ```java
   // Example: Create a cache with LRU eviction policy and capacity 100
   EvictionPolicy<String> evictionPolicy = new LRUPolicy<>(100);
   Storage<String, Object> storage = new ConcurrentHashMapStorage<>();
   Cache<String, Object> cache = new Cache<>(evictionPolicy, storage, true);
   ```
3. **Operations**: Perform cache operations such as put (adding key-value pairs) and get (retrieving values).

   ```
   // Example: Adding data to cache
   cache.put("key1", "value1");

   // Example: Retrieving data from cache
   Object value = cache.get("key1");

   ```

4. **Customization**: Implement custom eviction policies by extending EvictionPolicy interface and injecting them into the Cache constructor.

5. **Testing**: Run unit tests provided in the src/test directory or create your own tests to ensure proper functionality.
