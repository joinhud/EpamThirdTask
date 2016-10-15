package com.epam.third.action;

import com.epam.third.entity.Port;
import com.epam.third.exception.PortActionException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PortAction {
    private Lock lock = new ReentrantLock();

    public boolean loadContainers(Port port, int containersCount) throws PortActionException {
        if (!checkPort(port)) {
            throw new PortActionException("Port object is null.");
        }
        if(containersCount > Port.STORAGE_MAX_SIZE) {
            throw new PortActionException("Ship load containers more than storage size");
        }

        boolean result = false;

        try{
            lock.lock();

            isStorageFull(port, containersCount);
            if(containersCount >= 0) {
                port.getStorage().addAndGet(containersCount);
                result = true;
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    public int unloadStorage(Port port, int containersCount) throws PortActionException {
        if (!checkPort(port)) {
            throw new PortActionException("Port object is null.");
        }

        int result = 0;

        try {
            lock.lock();

            if(containersCount >= 0) {
                if(containersCount >= port.getStorage().get()) {
                    result = port.getStorage().get();
                    port.getStorage().set(0);
                } else {
                    result = containersCount;
                    port.getStorage().addAndGet(-containersCount);
                }
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    private void isStorageFull(Port port, int containersCount) {
        int expected = port.getStorage().get() + containersCount;

        if (expected > Port.STORAGE_MAX_SIZE) {
            port.getStorage().set(0);
        }
    }

    private boolean checkPort(Port port) {
        return port != null;
    }
}
