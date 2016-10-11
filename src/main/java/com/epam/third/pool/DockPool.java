package com.epam.third.pool;

import com.epam.third.entity.Dock;
import com.epam.third.exception.DockPoolException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DockPool {
    private static final int POOL_SIZE = 10;

    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
    private final Queue<Dock> docks = new LinkedList<>();

    private Lock lock = new ReentrantLock();

    public DockPool(Queue<Dock> source) {
        docks.addAll(source);
    }

    public Dock getDock() throws DockPoolException {
        try {
            Dock result;
            semaphore.acquire();
            lock.lock();
            result = docks.poll();
            lock.unlock();
            return result;
        } catch (InterruptedException e) {
            throw new DockPoolException(e);
        }
    }

    public void returnDock(Dock dock) {
        lock.lock();
        docks.add(dock);
        semaphore.release();
        lock.unlock();
    }
}
