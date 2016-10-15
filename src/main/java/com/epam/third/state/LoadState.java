package com.epam.third.state;

import com.epam.third.action.PortAction;
import com.epam.third.entity.Port;
import com.epam.third.entity.Ship;
import com.epam.third.exception.PortActionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadState implements ShipState {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void workingWithPortStorage(Ship ship, Port port) {
        PortAction action = new PortAction();

        try {
            if (action.loadContainers(port, ship.getContainersCount())) {
                ship.setContainersCount(0);
                System.out.println("Ship \'" + ship.getName()
                        + "' was upload "
                        + ship.getContainersCount()
                        + " containers to port storage.");
            } else {
                System.out.println("Ship \'" + ship.getName()
                        + "' didn't upload containers to port storage");
            }
        } catch (PortActionException e) {
            LOG.error(e);
        }
    }
}
