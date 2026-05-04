package com.atymelancholy.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.atymelancholy.exception.TextProcessingException;

public class TextFileService {
    public String readText(String filePath) {
        try {
            return Files.readString(Path.of(filePath));
        } catch (IOException exception) {
            throw new TextProcessingException("Unable to read file: " + filePath, exception);
        }
    }
}
