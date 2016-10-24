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
    private final int MAX_STORAGE_SIZE = RandomUtil.randomShipContainers();

    private DockPool pool;
    private ShipStrategy strategy;
    private int containersCount;

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
        if (containersCount >= 0 && containersCount <= MAX_STORAGE_SIZE) {
            this.containersCount = containersCount;
        } else {
            this.containersCount = MAX_STORAGE_SIZE;
        }
    }

    public int getMaxStorageSize() {
        return MAX_STORAGE_SIZE;
    }

    @Override
    public String toString() {
        return "Ship \'" + this.getName() +
                "\' {containers = " + containersCount +
                ", maxSize = " + MAX_STORAGE_SIZE +
                '}';
    }
}
