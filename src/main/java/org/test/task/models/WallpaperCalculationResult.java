package org.test.task.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class WallpaperCalculationResult {

    private final long total;
    private final List<Room> cubic;
    private final Set<Room> repeated;

    public WallpaperCalculationResult(long total, List<Room> cubic, Set<Room> repeated) {
        this.total = total;
        this.cubic = new ArrayList<>(cubic);
        this.repeated = new HashSet<>(repeated);
    }

    public long getTotal() {
        return total;
    }

    public List<Room> getCubic() {
        return new ArrayList<>(cubic);
    }

    public Set<Room> getRepeated() {
        return new HashSet<>(repeated);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "WallpaperCalculationResult{" + System.lineSeparator() +
                "total=" + total + ", " + System.lineSeparator() +
                "cubic=" + cubic + ", " + System.lineSeparator() +
                "repeated=" + repeated + ", " + System.lineSeparator() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, cubic, repeated);
    }
}
