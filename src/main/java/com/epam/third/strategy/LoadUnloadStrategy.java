package com.epam.third.strategy;

import com.epam.third.action.PortAction;
import com.epam.third.entity.Port;
import com.epam.third.entity.Ship;
import com.epam.third.exception.PortActionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadUnloadStrategy implements ShipStrategy {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void workingWithPortStorage(Ship ship, Port port) throws InterruptedException {
        PortAction action = new PortAction();
        int loadedContainers;
        int unloadedContainers;

        try {
            if (action.loadContainers(port, ship.getContainersCount())) {
                loadedContainers = ship.getContainersCount();
                ship.setContainersCount(0);
                unloadedContainers = action.unloadStorage(port, ship.getMaxStorageSize());
                ship.addContainers(unloadedContainers);
                System.out.println(ship
                        + " was load "
                        + loadedContainers
                        + " containers and unload "
                        + unloadedContainers
                        + " containers from port storage.");
            } else {
                System.out.println(ship + " didn't load containers to port storage");
            }
        } catch (PortActionException e) {
            LOG.error(e);
        }
    }
}
