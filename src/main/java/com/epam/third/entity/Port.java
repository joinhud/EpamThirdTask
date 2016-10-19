package com.epam.third.entity;

import com.epam.third.pool.DockPool;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    public static final int STORAGE_MAX_SIZE = 500;
    private static final int DOCKS_COUNT = 11;
    private static Port instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();

    private DockPool docks;
    private AtomicInteger storage;

    private Port() {
        initDocks();
        storage = new AtomicInteger(0);
    }

    private void initDocks() {
        ArrayDeque<Dock> deque = new ArrayDeque<>();

        for(int i = 1; i < DOCKS_COUNT; i++) {
            deque.add(new Dock(this, i));
        }

        docks = new DockPool(deque);
    }

    public static Port getInstance() {
        if(!instanceCreated.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new Port();
                    instanceCreated.getAndSet(true);
                }
            }  finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public DockPool getDocks() {
        return docks;
    }

    public AtomicInteger getStorage() {
        return storage;
    }

    public void setStorageValue(int value) {
        if(value <= STORAGE_MAX_SIZE) {
            storage.getAndSet(value);
        } else {
            storage.getAndSet(STORAGE_MAX_SIZE);
        }
    }

}
