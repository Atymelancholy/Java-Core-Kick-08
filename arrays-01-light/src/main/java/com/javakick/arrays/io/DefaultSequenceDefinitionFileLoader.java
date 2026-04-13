package com.javakick.arrays.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class DefaultSequenceDefinitionFileLoader implements SequenceDefinitionFileLoader {

    @Override
    public List<String> loadLines(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
}
