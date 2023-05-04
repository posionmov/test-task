package org.test.task;

import org.test.task.exception.InvalidStartArgumentException;
import org.test.task.models.Room;
import org.test.task.services.impl.RoomFileService;
import org.test.task.services.impl.RoomWallpaperService;
import org.test.task.services.interfaces.FileService;
import org.test.task.services.interfaces.WallpaperService;

public class WallpaperOrder {

    private static final int ARGUMENT_NUMBER_PATH = 0;

    private static final FileService<Room> FILE_SERVICE = new RoomFileService();

    private static final WallpaperService<Room> WALLPAPER_SERVICE = new RoomWallpaperService();

    public static void main(String[] args) {
        var path = getPathOrThrowException(args);
        var rooms = FILE_SERVICE.readInputFile(path);
        var result = WALLPAPER_SERVICE.calculateResult(rooms);
        System.out.println(result);
    }

    private static String getPathOrThrowException(String[] args) {
        if (args == null || args.length == 0 || args[ARGUMENT_NUMBER_PATH] == null) {
            throw new InvalidStartArgumentException();
        }
        return args[ARGUMENT_NUMBER_PATH];
    }
}
