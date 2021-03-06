package com.epam.fourth.file;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWorker {
    private static final Logger LOG = LogManager.getLogger();

    public String readAllFile(String path) {
        String result;

        try {
            result = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            LOG.log(Level.FATAL, e);
            throw new RuntimeException();
        }

        return result;
    }

    public void addDataToFile(String path, String data) {
        try {
            Files.write(Paths.get(path), data.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            LOG.log(Level.FATAL, e);
            throw new RuntimeException();
        }
    }
}
