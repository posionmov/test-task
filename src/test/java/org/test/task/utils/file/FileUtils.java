package org.test.task.utils.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class FileUtils {
    public static String createFile(Path sharedTempDir, String fileName, String fileContent) {
        var file = new File(sharedTempDir.toFile(), fileName);
        try (var writer = new FileWriter(file)) {
            writer.write(fileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.getAbsolutePath();
    }

    public static String prepareStringWithLineSeparation(List<String> lines) {
        return String.join(System.lineSeparator(), lines);
    }
}
