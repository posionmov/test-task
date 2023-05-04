package org.test.task.services.impl;

import org.test.task.exception.FileReadException;
import org.test.task.models.Room;
import org.test.task.services.interfaces.FileService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomFileService implements FileService<Room> {

    private static final Logger LOG = Logger.getLogger(RoomFileService.class.getName());

    private static final String ERROR_INVALID_TOKEN = "Tokens: |{0}| contains invalid value. Message: |{1}|";
    private static final String ERROR_EMPTY_LINE = "Line |{0}| is null or empty";
    private static final String ERROR_TOKEN_SIZE = "Tokens: |{0}| are not valid";

    private static final String SEPARATOR = "x";

    private static final int TOTAL_AMOUNT_OF_TOKENS = 3;
    private static final int LENGTH_INDEX = 0;
    private static final int WIDTH_INDEX = 1;
    private static final int HEIGHT_INDEX = 2;

    @Override
    public List<Room> readInputFile(String filename) {
        var rooms = new ArrayList<Room>();
        try (var reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
             var scanner = new Scanner(reader)) {
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (!isLineLengthValid(line)) {
                    continue;
                }

                var tokens = line.split(SEPARATOR);
                if (!isSeparatedLineIsValid(tokens)) {
                    continue;
                }
                var room = prepareRoomFromTokens(tokens);
                if (room != null) {
                    rooms.add(room);
                }
            }
        } catch (IOException e) {
            throw new FileReadException(e);
        }
        return rooms;
    }

    private static Room prepareRoomFromTokens(String[] tokens) {
        try {
            var lengthFromToken = Double.parseDouble(tokens[LENGTH_INDEX]);
            var widthFromToken = Double.parseDouble(tokens[WIDTH_INDEX]);
            var heightFromToken = Double.parseDouble(tokens[HEIGHT_INDEX]);
            return new Room(lengthFromToken, widthFromToken, heightFromToken);
        } catch (NumberFormatException e) {
            LOG.log(Level.WARNING, ERROR_INVALID_TOKEN, new Object[] {Arrays.toString(tokens), e.getMessage()});
            return null;
        }
    }

    private static boolean isLineLengthValid(String line) {
        if (line.length() == 0) {
            LOG.log(Level.WARNING, ERROR_EMPTY_LINE, line);
            return false;
        }
        return true;
    }

    private static boolean isSeparatedLineIsValid(String[] tokens) {
        if (tokens.length != TOTAL_AMOUNT_OF_TOKENS) {
            LOG.log(Level.WARNING, ERROR_TOKEN_SIZE, Arrays.toString(tokens));
            return false;
        }
        return true;
    }
}
