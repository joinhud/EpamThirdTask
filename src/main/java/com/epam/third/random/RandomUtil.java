package com.epam.third.random;

import com.epam.third.strategy.LoadStrategy;
import com.epam.third.strategy.LoadUnloadStrategy;
import com.epam.third.strategy.ShipStrategy;
import com.epam.third.strategy.UnloadStrategy;

import java.util.Random;

public class RandomUtil {
    private static final int MAX_SHIP_CONTAINERS = 200;
    private static final int MAX_PORT_SIZE = 500;
    private static final int STATE_COUNT = 3;
    private static final Random random = new Random();

    public static int randomShipContainers() {
        return random.nextInt(MAX_SHIP_CONTAINERS);
    }

    public static int randomPortContainers() {
        return random.nextInt(MAX_PORT_SIZE);
    }

    public static ShipStrategy randomShipStrategy() {
        int state = Math.abs(random.nextInt(STATE_COUNT));
        ShipStrategy result;

        switch (state) {
            case 0:
                result = new LoadStrategy();
                break;
            case 1:
                result = new UnloadStrategy();
                break;
            case 2:
                result = new LoadUnloadStrategy();
                break;
            default:
                result = new LoadStrategy();
        }

        return result;
    }
}
