package com.epam.fourth.file;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileWorkerTest {
    private static FileWorker worker;

    @BeforeClass
    public static void initFileWorker() {
        worker = new FileWorker();
    }

    @Test(expected = RuntimeException.class)
    public void readAllFileTestIncorrectPath() {
        String actual = null;
        actual = worker.readAllFile("data/testReadLines.txt");
        Assert.assertNull(actual);
    }

    @Test(expected = RuntimeException.class)
    public void readAllFileTestNullPath() {
        String actual = null;
        actual = worker.readAllFile(null);
        Assert.assertNull(actual);
    }

    @Test
    public void readAllFileTestResult() {
        String actual = null;
        actual = worker.readAllFile("data/text.txt");
        Assert.assertNotNull(actual);
    }

    @Test(expected = RuntimeException.class)
    public void addDataToFileTestNullPath() {
        worker.addDataToFile(null, "Data");
    }

    @Test(expected = RuntimeException.class)
    public void addDataToFileTestNullData() {
        worker.addDataToFile("data/testAddDataToFile.txt", null);
    }

    @Test(expected = RuntimeException.class)
    public void addDataToFileTestNullPathAndData() {
        worker.addDataToFile(null, null);
    }

    @Test
    public void addDataToFileTestCorrectParams() {
        worker.addDataToFile("data/testAddDataToFile.txt", "Data");
    }
}