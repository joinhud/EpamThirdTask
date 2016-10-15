package com.epam.third.state;

import com.epam.third.entity.Port;
import com.epam.third.entity.Ship;

public interface ShipState {
    public void workingWithPortStorage(Ship ship, Port port) throws InterruptedException;
}
