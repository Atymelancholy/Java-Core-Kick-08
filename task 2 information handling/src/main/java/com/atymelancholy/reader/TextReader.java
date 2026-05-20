package com.atymelancholy.reader;

import com.atymelancholy.exception.TextProcessingException;

public interface TextReader {

    String read(String filePath)
            throws TextProcessingException;
}