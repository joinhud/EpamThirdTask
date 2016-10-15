package com.epam.third.state;

import com.epam.third.action.PortAction;
import com.epam.third.entity.Port;
import com.epam.third.entity.Ship;
import com.epam.third.exception.PortActionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnloadState implements ShipState {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void workingWithPortStorage(Ship ship, Port port) throws InterruptedException {
        PortAction action = new PortAction();

        try {
            int unloadedContainersCount = ship.getMaxStorageSize() - ship.getContainersCount();
            ship.addContainers(action.unloadStorage(port, unloadedContainersCount));
            System.out.println("Ship \'" + ship.getName()
                    + "' was unload containers from port storage.");
        } catch (PortActionException e) {
            LOG.error(e);
        }
    }
}
