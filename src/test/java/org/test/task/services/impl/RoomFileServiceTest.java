package org.test.task.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.test.task.exception.FileReadException;
import org.test.task.models.Room;
import org.test.task.services.interfaces.FileService;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.test.task.utils.file.FileUtils.createFile;
import static org.test.task.utils.file.FileUtils.prepareStringWithLineSeparation;
import static org.test.task.utils.wallpaper.WallpaperTestUtils.prepareRoom;

class RoomFileServiceTest {

    private static final FileService<Room> serviceToTest = new RoomFileService();

    @TempDir
    private Path sharedTempDir;

    @ParameterizedTest
    @MethodSource("fileContentAndResultProvider")
    void whenPassParamsThenReturnExpectedValues(String fileName, String fileContent, List<Room> expectedResult) {
        // given
        var path = createFile(sharedTempDir, fileName, fileContent);

        // when
        var result = assertDoesNotThrow(
                () -> serviceToTest.readInputFile(path),
                "'readInputFile' should not throw exception");

        // then
        assertNotNull(result, "result should exist");
        assertEquals(expectedResult.size(), result.size(), "result array should contain expected size of values");

        for (int i = 0; i < result.size(); i++) {
            var resultElement = result.get(i);
            var expectedElement = expectedResult.get(i);
            assertNotNull(resultElement, "result element should exist");
            assertEquals(expectedElement, resultElement, "elements should be equals");
        }
    }

    @Test
    void whenPassParamsWithInvalidValueInArgsThenShouldThrowException() {
        // given
        var path = "invalidPath";

        // when
        assertThrows(
                FileReadException.class,
                () -> serviceToTest.readInputFile(path),
                "when call method with invalid path then should throw FileReadException"
        );
    }

    @Test
    void whenPassRealFileThenShouldReturnExpectedAmountOfRooms() {
        // given
        var path = "src/test/resources/sample-input.txt";
        var expectedSize = 1000;

        // when
        var result = assertDoesNotThrow(
                () -> serviceToTest.readInputFile(path),
                "'readInputFile' should not throw exception");

        // then
        assertEquals(expectedSize, result.size(), "result size should be expected");
    }

    private static Stream<Arguments> fileContentAndResultProvider() {
        return Stream.of(
                Arguments.of(
                        "invalid",
                        "invalid value",
                        List.of()),
                Arguments.of(
                        "empty",
                        "",
                        List.of()),
                Arguments.of(
                        "oneLine",
                        "1x1x5",
                        List.of(prepareRoom(1, 1, 5))),
                Arguments.of(
                        "twoLines",
                        prepareStringWithLineSeparation(List.of("1x1x5", "1x1x5")),
                        List.of(prepareRoom(1, 1, 5), prepareRoom(1, 1, 5))),
                Arguments.of(
                        "twoLinesWithEmptyLine",
                        prepareStringWithLineSeparation(List.of("1x1x5", "", "1x1x5")),
                        List.of(prepareRoom(1, 1, 5), prepareRoom(1, 1, 5))),
                Arguments.of(
                        "validAndInvalidLines",
                        prepareStringWithLineSeparation(List.of("1x1x5", "invalid")),
                        List.of(prepareRoom(1, 1, 5))),
                Arguments.of(
                        "lineContainInvalidValue",
                        prepareStringWithLineSeparation(List.of("1x1xqw", "invalid")),
                        List.of()),
                Arguments.of(
                        "cineDontContainAllValues",
                        prepareStringWithLineSeparation(List.of("1x1x")),
                        List.of())
        );
    }
}
