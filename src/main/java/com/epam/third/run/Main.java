package com.epam.third.run;

import com.epam.third.entity.Port;
import com.epam.third.entity.Ship;
import com.epam.third.random.RandomUtil;

import java.util.Random;

public class Main {
    public static void main(String... param) {
        Port port = Port.getInstance();
        Random random = new Random();

        port.setStorageValue(RandomUtil.randomPortContainers());

        for (int i = 0; i < 40; i++) {
            new Ship(port.getDocks(), RandomUtil.randomShipState(),
                    RandomUtil.randomShipContainers(), RandomUtil.randomShipContainers()).start();
        }
    }
}
