package com.epam.third.entity;

import com.epam.third.exception.DockPoolException;
import com.epam.third.pool.DockPool;
import com.epam.third.random.RandomUtil;
import com.epam.third.strategy.ShipStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Ship extends Thread {
    private static final Logger LOG = LogManager.getLogger();
    private DockPool pool;
    private ShipStrategy strategy;
    private int containersCount;
    private final int maxStorageSize = RandomUtil.randomShipContainers();

    public Ship(DockPool pool, ShipStrategy strategy, int containersCount) {
        this.pool = pool;
        this.strategy = strategy;
        setContainersCount(containersCount);
    }

    @Override
    public void run() {
        Dock dock = null;

        try {
            dock = pool.getDock();
            System.out.println(this.getName() + " in dock № " + dock.getDockId());
            strategy.workingWithPortStorage(this, dock.getPort());
            System.out.println("Port storage: "
                    + dock.getPort().getStorage().get()
                    + ", after ship \'"
                    + this.getName() + "\'");
            TimeUnit.SECONDS.sleep(2);
        } catch (DockPoolException | InterruptedException e) {
            LOG.error(e);
        } finally {
            pool.returnDock(dock);
        }
    }

    public boolean addContainers(int containersCount) {
        boolean result = false;

        if (containersCount >= 0) {
            this.containersCount += containersCount;
            result = true;
        }

        return result;
    }

    public int getContainersCount() {
        return containersCount;
    }

    public void setContainersCount(int containersCount) {
        if (containersCount >= 0 && containersCount <= maxStorageSize) {
            this.containersCount = containersCount;
        } else {
            this.containersCount = maxStorageSize;
        }
    }

    public int getMaxStorageSize() {
        return maxStorageSize;
    }

    @Override
    public String toString() {
        return "Ship \'" + this.getName() +
                "\' {containers = " + containersCount +
                ", maxSize = " + maxStorageSize +
                '}';
    }
}
