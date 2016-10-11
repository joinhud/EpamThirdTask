package com.epam.third.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static Port instance = null;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static final Logger LOG = LogManager.getRootLogger();
    private static final int DOCKS_COUNT = 10;

    private ArrayDeque<Dock> docks = new ArrayDeque<>(DOCKS_COUNT);
    private int storage;

    private Port() {
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
}
