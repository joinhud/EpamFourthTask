package com.epam.fourth.comparator;

import com.epam.fourth.action.CompositeAction;
import com.epam.fourth.composite.TextComponent;
import com.epam.fourth.exception.CompositeActionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class CompositeCountLexemeComparator implements Comparator<TextComponent> {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        CompositeAction action = new CompositeAction();
        int result = 0;
        try {
            result = action.calculateLexemesCount(o1) > action.calculateLexemesCount(o2) ? 1 : -1;
        } catch (CompositeActionException e) {
            LOG.error(e);
        }
        return result;
    }
}
