package com.epam.third.entity;

public class Dock {

    private Port port;

    public Dock(Port port) {
        this.port = port;
    }

    public Port getPort() {
        return port;
    }
}
