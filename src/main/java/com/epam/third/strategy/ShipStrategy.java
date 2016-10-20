package com.epam.third.strategy;

import com.epam.third.entity.Port;
import com.epam.third.entity.Ship;

public interface ShipStrategy {
    void workingWithPortStorage(Ship ship, Port port) throws InterruptedException;
}
