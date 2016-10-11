package com.epam.third.action;

import com.epam.third.entity.Port;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PortAction {

    private Lock lock = new ReentrantLock();

    public boolean loadContainers(Port port, int containersCount) {
        lock.lock();
        boolean result = false;

        isStorageFull(port, containersCount);
        if(containersCount >= 0) {
            port.getStorage().addAndGet(containersCount);
            result = true;
        }

        lock.unlock();
        return result;
    }

    public int unloadStorage(Port port, int containersCount) {
        lock.lock();
        int result = 0;

        if(containersCount >= 0) {
            if(containersCount >= port.getStorage().get()) {
                result = port.getStorage().get();
                port.getStorage().set(0);
            } else {
                result = containersCount;
                port.getStorage().addAndGet(-containersCount);
            }
        }

        lock.unlock();
        return result;
    }

    private void isStorageFull(Port port, int containersCount) {
        int expected = port.getStorage().get() + containersCount;

        if (expected > port.getMaxStorageSize()) {
            port.getStorage().set(0);
        }
    }
}
