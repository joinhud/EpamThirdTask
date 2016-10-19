package com.epam.third.run;

import com.epam.third.entity.Port;
import com.epam.third.entity.Ship;
import com.epam.third.random.RandomUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String... param) {
        LOG.info("Application start.");

        Port port = Port.getInstance();
        port.setStorageValue(RandomUtil.randomPortContainers());
        System.out.println("In port storage: " + port.getStorage().get() + " containers");

        for (int i = 0; i < 20; i++) {
            new Ship(port.getDocks(), RandomUtil.randomShipStrategy(), RandomUtil.randomShipContainers()).start();
        }

        LOG.info("Application end.");
    }
}
