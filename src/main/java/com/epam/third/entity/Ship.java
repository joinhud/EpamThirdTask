package com.epam.third.entity;

import com.epam.third.exception.DockPoolException;
import com.epam.third.pool.DockPool;
import com.epam.third.state.ShipState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Ship extends Thread {
    private static final Logger LOG = LogManager.getLogger();
    private DockPool pool;
    private ShipState state;
    private int containersCount;
    private int maxStorageSize; //сделать final

    public Ship(DockPool pool, ShipState state, int containersCount, int maxStorageSize) {
        this.pool = pool;
        this.state = state;
        setMaxStorageSize(maxStorageSize);
        setContainersCount(containersCount);
    }

    @Override
    public void run() {
        Dock dock = null;

        try {
            dock = pool.getDock();
            System.out.println("Storage size = " + dock.getPort().getStorage());
            System.out.println(this.getName() + " in dock № " + dock.getDockId());
            state.workingWithPortStorage(this, dock.getPort());
            TimeUnit.SECONDS.sleep(2);
        } catch (DockPoolException | InterruptedException e) {
            LOG.error(e);
        } finally {
            pool.returnDock(dock);
        }
    }

    public boolean addContainers(int containersCount) {
        boolean result = false;

        if(containersCount >= 0) {
            this.containersCount += containersCount;
            result = true;
        }

        return result;
    }

    public int getContainersCount() {
        return containersCount;
    }

    public void setContainersCount(int containersCount) {
        if(containersCount >= 0 && containersCount <= maxStorageSize) {
            this.containersCount = containersCount;
        } else {
            this.containersCount = maxStorageSize;
        }
    }

    public int getMaxStorageSize() {
        return maxStorageSize;
    }

    //вынести в конструктор
    public void setMaxStorageSize(int maxStorageSize) {
        if(maxStorageSize >= 0 && maxStorageSize >= containersCount) {
            this.maxStorageSize = maxStorageSize;
        } else {
            this.maxStorageSize = containersCount;
        }
    }
}
