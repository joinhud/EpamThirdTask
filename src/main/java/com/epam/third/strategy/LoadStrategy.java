package com.epam.third.strategy;

import com.epam.third.action.PortAction;
import com.epam.third.entity.Port;
import com.epam.third.entity.Ship;
import com.epam.third.exception.PortActionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadStrategy implements ShipStrategy {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void workingWithPortStorage(Ship ship, Port port) {
        PortAction action = new PortAction();

        try {
            if (action.loadContainers(port, ship.getContainersCount())) {
                System.out.println(ship
                        + " was load "
                        + ship.getContainersCount()
                        + " containers to port storage.");
                ship.setContainersCount(0);
            } else {
                System.out.println(ship + " didn't load containers to port storage");
            }
        } catch (PortActionException e) {
            LOG.error(e);
        }
    }
}
