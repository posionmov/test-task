package org.test.task.services.interfaces;

import java.util.List;

public interface FileService<T> {
    List<T> readInputFile(String filename);
}
