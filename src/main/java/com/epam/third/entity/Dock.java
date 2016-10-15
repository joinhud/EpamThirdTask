package com.epam.third.entity;

public class Dock {
    private long dockId;
    private Port port;

    public Dock(Port port, long dockId) {
        this.port = port;
        setDockId(dockId);
    }

    public Port getPort() {
        return port;
    }

    public long getDockId() {
        return dockId;
    }

    public void setDockId(long dockId) {
        this.dockId = dockId >= 0 ? dockId : 0;
    }
}
