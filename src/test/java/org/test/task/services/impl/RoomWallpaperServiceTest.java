package org.test.task.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.test.task.models.Room;
import org.test.task.models.WallpaperCalculationResult;
import org.test.task.services.interfaces.WallpaperService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.test.task.utils.wallpaper.WallpaperTestUtils.prepareResult;
import static org.test.task.utils.wallpaper.WallpaperTestUtils.prepareRoom;

class RoomWallpaperServiceTest {

    private static final WallpaperService<Room> serviceToTest = new RoomWallpaperService();

    @Test
    void whenPassEmptyArrayThenShouldReturnDefaultValue() {
        // given
        var input = new ArrayList<Room>();

        // when
        var result = assertDoesNotThrow(
                () -> serviceToTest.calculateResult(input),
                "'validate' should not throw exception");

        // then
        assertNotNull(result, "Result should be not null");
        assertNotNull(result.getCubic(), "Result should be not null");
        assertNotNull(result.getRepeated(), "Result should be not null");
        assertEquals(0, result.getTotal(), "Value of field 'total' should be expected");
        assertTrue(result.getCubic().isEmpty(), "Array of 'cubic' should be empty");
        assertTrue(result.getRepeated().isEmpty(), "Set of 'cubic' should be empty");
    }

    @ParameterizedTest
    @MethodSource("inputOutputProvider")
    void whenPassValidDataThenReturnExpectedResult(List<Room> rooms, WallpaperCalculationResult expectedResult) {
        // when
        var result = assertDoesNotThrow(
                () -> serviceToTest.calculateResult(rooms),
                "'validate' should not throw exception");

        // then
        assertNotNull(result, "Result should be not null");
        assertNotNull(result.getCubic(), "Result should be not null");
        assertNotNull(result.getRepeated(), "Result should be not null");
        assertEquals(expectedResult.getTotal(), result.getTotal(), "Value of field 'total' should be expected");

        assertEquals(expectedResult.getCubic(), result.getCubic(), "'Cubic' field should be expected");
        assertEquals(expectedResult.getRepeated(), result.getRepeated(), "'Repeated' field should be expected");
    }

    public static Stream<Arguments> inputOutputProvider() {
        var firstResult = 24;
        var secondResult = 23;
        var thirdResult = firstResult * 2 + secondResult;
        var fourResult = firstResult * 2 + secondResult * 2;
        var fifthResult = 7;
        var sixResult = fourResult + fifthResult;

        var expectedRoom1 = prepareRoom(1, 2, 3);
        var expectedRoom2 = prepareRoom(1, 1, 5);
        var expectedRoom3 = prepareRoom(1, 1, 1);

        return Stream.of(
                Arguments.of(
                        List.of(expectedRoom1),
                        prepareResult(firstResult)),
                Arguments.of(
                        List.of(expectedRoom2),
                        prepareResult(secondResult)),
                Arguments.of(
                        List.of(expectedRoom1, expectedRoom2, expectedRoom1),
                        prepareResult(thirdResult, Set.of(expectedRoom1))),
                Arguments.of(
                        List.of(expectedRoom1, expectedRoom2, expectedRoom1, expectedRoom2),
                        prepareResult(fourResult, Set.of(expectedRoom1, expectedRoom2))),
                Arguments.of(
                        List.of(expectedRoom3),
                        prepareResult(fifthResult, List.of(expectedRoom3))),
                Arguments.of(
                        List.of(expectedRoom1, expectedRoom2, expectedRoom1, expectedRoom2, expectedRoom3),
                        prepareResult(sixResult, List.of(expectedRoom3), Set.of(expectedRoom1, expectedRoom2)))
        );
    }
}
