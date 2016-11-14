package com.epam.fourth.report;

import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.CompositeException;
import com.epam.fourth.exception.TextActionException;
import com.epam.fourth.file.FileWorker;

public class Report {
    private static final String FILE_PATH = "data/report.txt";
    private static final String TEXT_TITLE = "------------------------ Text ------------------------\n";
    private static final String SORTED_TEXT_TITLE = "------------------------ Sorted Text ------------------------\n";
    private static final String ENDER = "------------------------------------------------------\n";

    private FileWorker worker;

    public Report() {
        worker = new FileWorker();
    }

    public void writeTextCompositeDataReport(TextComposite text) throws CompositeException {
        if (text == null) {
            throw new CompositeException("TextComposite object is null.");
        }

        worker.addDataToFile(FILE_PATH, TEXT_TITLE);
        worker.addDataToFile(FILE_PATH, text.toString());
        worker.addDataToFile(FILE_PATH, ENDER);
    }

    public void writeSortedTextCompositeReport(String data) throws TextActionException {
        if (data == null) {
            throw new TextActionException("Result string is null");
        }

        worker.addDataToFile(FILE_PATH, SORTED_TEXT_TITLE);
        worker.addDataToFile(FILE_PATH, data);
        worker.addDataToFile(FILE_PATH, ENDER);
    }
}
