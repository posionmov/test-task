package org.test.task.models;

import java.util.Objects;

public class Room implements Comparable<Room> {

    private final double l;
    private final double w;
    private final double h;

    public Room(double l, double w, double h) {
        this.l = l;
        this.w = w;
        this.h = h;
    }

    public int getSurfaceArea() {
        return (int) (2 * l * w + 2 * w * h + 2 * h * l);
    }

    public int getSmallestSideArea() {
        int area1 = (int) (l * w);
        int area2 = (int) (w * h);
        int area3 = (int) (h * l);
        return Math.min(Math.min(area1, area2), area3);
    }

    public boolean isCubic() {
        return l == w && w == h;
    }

    @Override
    public int compareTo(Room o) {
        int currentSize = calculateTotalAmount(this);
        int compareToSizeSize = calculateTotalAmount(o);
        return Integer.compare(currentSize, compareToSizeSize);
    }

    private static int calculateTotalAmount(Room room) {
        int smallestSideArea = room.getSmallestSideArea();
        int surfaceArea = room.getSurfaceArea();
        return surfaceArea + smallestSideArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var room = (Room) o;
        return Double.compare(room.l, l) == 0 &&
                Double.compare(room.w, w) == 0 &&
                Double.compare(room.h, h) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(l, w, h);
    }

    @Override
    public String toString() {
        return "Room{" +
                "l=" + l +
                ", w=" + w +
                ", h=" + h +
                '}';
    }
}
