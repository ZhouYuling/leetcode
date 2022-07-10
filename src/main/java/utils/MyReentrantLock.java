package utils;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReentrantLock {

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        //AbstractQueuedSynchronizer abstractQueuedSynchronizer = new AbstractQueuedSynchronizer();

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

        ConcurrentHashMap<String, String> stringStringConcurrentHashMap = new ConcurrentHashMap<>();

        HashSet<String> hashSet = new HashSet<>();
        TreeSet<String> strings = new TreeSet<>();

    }

}
