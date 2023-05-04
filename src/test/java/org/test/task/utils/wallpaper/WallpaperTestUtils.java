package org.test.task.utils.wallpaper;

import org.test.task.models.Room;
import org.test.task.models.WallpaperCalculationResult;
import java.util.List;
import java.util.Set;

public class WallpaperTestUtils {

    public static Room prepareRoom(double l, double w, double h) {
        return new Room(l, w, h);
    }

    public static WallpaperCalculationResult prepareResult(long total) {
        return new WallpaperCalculationResult(total, List.of(), Set.of());
    }

    public static WallpaperCalculationResult prepareResult(long total, List<Room> cubic) {
        return new WallpaperCalculationResult(total, cubic, Set.of());
    }

    public static WallpaperCalculationResult prepareResult(long total, Set<Room> repeated) {
        return new WallpaperCalculationResult(total, List.of(), repeated);
    }

    public static WallpaperCalculationResult prepareResult(long total, List<Room> cubic, Set<Room> repeated) {
        return new WallpaperCalculationResult(total, cubic, repeated);
    }
}
