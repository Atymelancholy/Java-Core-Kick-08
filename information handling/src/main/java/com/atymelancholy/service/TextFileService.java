package com.atymelancholy.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atymelancholy.exception.TextProcessingException;

public class TextFileService {
    private static final Logger logger = LoggerFactory.getLogger(TextFileService.class);

    public String readText(String filePath) {
        try {
            String content = Files.readString(Path.of(filePath));
            logger.info("File read successfully: {} ({} chars)", filePath, content.length());
            return content;
        } catch (IOException exception) {
            logger.error("Failed to read file: {}", filePath, exception);
            throw new TextProcessingException("Unable to read file: " + filePath, exception);
        }
    }
}