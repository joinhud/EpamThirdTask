package com.epam.third.run;

import com.epam.third.entity.Port;
import com.epam.third.entity.Ship;

import java.util.Random;

public class Main {
    public static void main(String... param) {
        Port port = Port.getInstance();
        Random random = new Random();

        port.setStorageValue(230);

        for(int i = 0; i < 19; i++) {
            new Ship(port.getDocks(), random.nextBoolean()).start();
        }
        Ship ship = new Ship(port.getDocks(), random.nextBoolean());
        ship.start();
        try {
            ship.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Port storage = " + port.getStorage().get());
    }
}
