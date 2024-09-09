package com.sk;    

import com.sk.cache.Cache;
import com.sk.cache.policy.EvictionPolicy;
import com.sk.cache.policy.FifoEvictionPolicy;
import com.sk.cache.policy.LifoEvictionPolicy;
import com.sk.cache.policy.LRUPolicy;
import com.sk.cache.storage.ConcurrentHashMapStorage;
import com.sk.cache.storage.Storage;

import java.util.Scanner;

public class MainCacheApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Choose eviction policy
        System.out.println("Choose eviction policy:");
        System.out.println("1. FIFO (First In First Out)");
        System.out.println("2. LIFO (Last In First Out)");
        System.out.println("3. LRU (Least Recently Used)");
        System.out.print("Enter your choice: ");
        int policyChoice = scanner.nextInt();
        scanner.nextLine(); 

        EvictionPolicy<String> evictionPolicy = null;
       

        switch (policyChoice) {
            case 1:
                evictionPolicy = new FifoEvictionPolicy<>();
                break;
            case 2:
                evictionPolicy = new LifoEvictionPolicy<>();
                break;
            case 3:
                evictionPolicy = new LRUPolicy<>();
                
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                return;
        }

        // Choose cache capacity
        System.out.print("Enter cache capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); 
        Storage<String, String> storage = new ConcurrentHashMapStorage<>(capacity);

        // Create cache with chosen policy and storage
        Cache<String, String> cache = new Cache<>(evictionPolicy, storage);

        // Perform operations on the cache
        boolean continueExecution = true;
        while (continueExecution) {
            System.out.println("\nChoose operation:");
            System.out.println("1. Put key-value pair");
            System.out.println("2. Get value for key");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int operationChoice = scanner.nextInt();
            scanner.nextLine();

            switch (operationChoice) {
                case 1:
                    System.out.print("Enter key: ");
                    String key = scanner.nextLine();
                    System.out.print("Enter value: ");
                    String value = scanner.nextLine();
                    cache.put(key, value);
                    System.out.println("Key-value pair added to cache.");
                    break;
                case 2:
                    System.out.print("Enter key to get value: ");
                    String getKey = scanner.nextLine();
                    String retrievedValue = cache.get(getKey);
                    if (retrievedValue != null) {
                        System.out.println("Value for key '" + getKey + "': " + retrievedValue);
                    } else {
                        System.out.println("No value found for key '" + getKey + "'.");
                    }
                    break;
                case 3:
                    continueExecution = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }

        System.out.println("Exiting cache application.");
        scanner.close();
    }
}
