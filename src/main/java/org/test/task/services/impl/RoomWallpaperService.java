package org.test.task.services.impl;

import org.test.task.models.Room;
import org.test.task.models.WallpaperCalculationResult;
import org.test.task.services.interfaces.WallpaperService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoomWallpaperService implements WallpaperService<Room> {

    @Override
    public WallpaperCalculationResult calculateResult(List<Room> rooms) {
        var uniqueRooms = new HashSet<Room>();
        var total = 0;
        var cubic = new ArrayList<Room>();
        var repeated = new HashSet<Room>();

        for (Room room : rooms) {
            total += calculateTotalRoomSize(room);
            addIfCubic(cubic, room);
            addIfRepeat(repeated, uniqueRooms, room);
        }
        return new WallpaperCalculationResult(total, cubic, repeated);
    }

    private static void addIfCubic(List<Room> cubicList, Room room) {
        if (room.isCubic()) {
            cubicList.add(room);
        }
        cubicList.sort(Collections.reverseOrder());
    }

    private static void addIfRepeat(Set<Room> repeated, Set<Room> uniqueRooms, Room room) {
        if (!uniqueRooms.add(room)) {
            repeated.add(room);
        }
    }

    private static int calculateTotalRoomSize(Room room) {
        var surfaceArea = room.getSurfaceArea();
        var smallestSideArea = room.getSmallestSideArea();
        return surfaceArea + smallestSideArea;
    }
}
