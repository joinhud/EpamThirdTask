package com.epam.third.pool;

import com.epam.third.entity.Dock;
import com.epam.third.exception.DockPoolException;

import java.beans.IntrospectionException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DockPool {
    private final Queue<Dock> docks = new ArrayDeque<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    public DockPool(Queue<Dock> source) {
        docks.addAll(source);
    }

    public Dock getDock() throws DockPoolException {
        Dock result;

        try {
            lock.lock();
            while (docks.isEmpty()) {
                notEmpty.await();
            }
            result = docks.poll();
        } catch (InterruptedException e) {
            throw new DockPoolException(e);
        } finally {
            lock.unlock();
        }

        return result;
    }

    public void returnDock(Dock dock) {
        try {
            lock.lock();
            docks.add(dock);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
}
