package org.test.task.services.interfaces;

import org.test.task.models.WallpaperCalculationResult;
import java.util.List;

public interface WallpaperService<T> {
    WallpaperCalculationResult calculateResult(List<T> rooms);
}
