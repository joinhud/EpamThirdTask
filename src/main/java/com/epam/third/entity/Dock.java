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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dock dock = (Dock) o;

        if (dockId != dock.dockId) return false;
        return port.equals(dock.port);

    }

    @Override
    public int hashCode() {
        int result = (int) (dockId ^ (dockId >>> 32));
        result = 31 * result + port.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Dock{" +
                "dockId=" + dockId +
                ", port=" + port +
                '}';
    }
}
