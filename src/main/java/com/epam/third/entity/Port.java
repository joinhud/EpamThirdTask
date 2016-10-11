package com.epam.third.entity;

import com.epam.third.pool.DockPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static Port instance = null;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static final Logger LOG = LogManager.getRootLogger();
    private static final int STORAGE_MAX_SIZE = 500;

    private DockPool docks;
    private AtomicInteger storage;
    private int maxStorageSize;


    private Port() {
        LinkedList<Dock> list = new LinkedList<>();

        for(int i = 0; i < 10; i++) {
            list.add(new Dock(this));
        }

        docks = new DockPool(list);
        storage = new AtomicInteger();
        maxStorageSize = STORAGE_MAX_SIZE;
    }

    public static Port getInstance() {
        if(!instanceCreated.get()) {
            try {
                lock.lock();
                if (!instanceCreated.get()) {
                    instance = new Port();
                    instanceCreated.set(true);
                }
            } catch (Exception e) {
                LOG.error(e);
            } finally {
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
        if(value <= maxStorageSize) {
            storage.set(value);
        } else {
            storage.set(maxStorageSize);
        }
    }

    public int getMaxStorageSize() {
        return maxStorageSize;
    }

}
