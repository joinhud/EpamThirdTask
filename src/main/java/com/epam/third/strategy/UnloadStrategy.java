package com.epam.third.strategy;

import com.epam.third.action.PortAction;
import com.epam.third.entity.Port;
import com.epam.third.entity.Ship;
import com.epam.third.exception.PortActionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnloadStrategy implements ShipStrategy {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void workingWithPortStorage(Ship ship, Port port) throws InterruptedException {
        PortAction action = new PortAction();

        try {
            int unloadContainersCount = ship.getMaxStorageSize() - ship.getContainersCount();
            int unloadedContainers = action.unloadStorage(port, unloadContainersCount);
            System.out.println(ship + " was unload " + unloadedContainers + " containers from port storage.");
            ship.addContainers(unloadedContainers);
        } catch (PortActionException e) {
            LOG.error(e);
        }
    }
}
