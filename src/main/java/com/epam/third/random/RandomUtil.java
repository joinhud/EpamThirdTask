package com.epam.third.random;

import com.epam.third.state.LoadState;
import com.epam.third.state.ShipState;
import com.epam.third.state.UnloadState;

import java.util.Random;

public class RandomUtil {
    private static final int MAX_SHIP_CONTAINERS = 200;
    private static final int MAX_PORT_SIZE = 500;
    private static final int STATE_COUNT = 2;
    private static Random random = new Random();

    public static int randomShipContainers() {
        return random.nextInt(MAX_SHIP_CONTAINERS);
    }

    public static int randomPortContainers() {
        return random.nextInt(MAX_PORT_SIZE);
    }

    public static ShipState randomShipState() {
        int state = Math.abs(random.nextInt(STATE_COUNT));
        ShipState result = null;

        switch (state) {
            case 1: result = new LoadState();
                break;
            case 2: result = new UnloadState();
                break;
            default:
                result = new LoadState();
        }

        return result;
    }
}
