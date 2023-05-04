package org.test.task;

import org.junit.jupiter.api.Test;
import org.test.task.exception.InvalidStartArgumentException;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WallpaperOrderTest {

    @Test
    void whenArgsAreNullThenShouldThrowException() {
        // then
        assertThrows(
                InvalidStartArgumentException.class,
                () -> WallpaperOrder.main(null),
                "when call method with null args then should throw exception"
        );
    }

    @Test
    void whenArgsAreEmptyArrayThenShouldThrowException() {
        // then
        assertThrows(
                InvalidStartArgumentException.class,
                () -> WallpaperOrder.main(new String[] {}),
                "when call method with empty array then should throw exception"
        );
    }

    @Test
    void whenArgsContainsNullInArrayThenShouldThrowException() {
        // then
        assertThrows(
                InvalidStartArgumentException.class,
                () -> WallpaperOrder.main(new String[] {null}),
                "when call method with null in array then should throw exception"
        );
    }
}
