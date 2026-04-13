package com.javakick.arrays.io;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface SequenceDefinitionFileLoader {

    List<String> loadLines(Path path) throws IOException;
}
