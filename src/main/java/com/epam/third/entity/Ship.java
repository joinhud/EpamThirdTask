package com.epam.third.entity;

import com.epam.third.action.PortAction;
import com.epam.third.exception.DockPoolException;
import com.epam.third.pool.DockPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Ship extends Thread {
    private static final Logger LOG = LogManager.getRootLogger();
    private DockPool pool;
    private boolean load;

    public Ship(DockPool pool, boolean load) {
        this.pool = pool;
        this.load = load;
    }

    @Override
    public void run() {
        Dock dock = null;
        PortAction action = new PortAction();
        try {
            dock = pool.getDock();
            if(load) {
                action.loadContainers(dock.getPort(), 50);
                System.out.println(this.getName() + " is load containers.");
                System.out.println(this.getName() + "# Port storage after loading = " + dock.getPort().getStorage().get());
            } else {
                action.unloadStorage(dock.getPort(), 20);
                System.out.println(this.getName() + " is unload port storage.");
                System.out.println(this.getName() + "# Port storage after unloading = " + dock.getPort().getStorage().get());
            }
            TimeUnit.SECONDS.sleep(2);
        } catch (DockPoolException | InterruptedException e) {
            LOG.error(e);
        } finally {
            if(dock != null) {
                pool.returnDock(dock);
            }
        }
    }
}
