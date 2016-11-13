package com.epam.fourth.chains;

import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.ChainHandlerException;

public abstract class AbstractChainHandler {
    protected AbstractChainHandler successor = DefaultHandlerRequest.getHandlerRequest();

    public AbstractChainHandler() {
    }

    public AbstractChainHandler(AbstractChainHandler successor) {
        this.successor = successor;
    }

    public void setSuccessor(AbstractChainHandler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(TextComposite composite, String textForParsing) throws ChainHandlerException;

    public abstract void chain(TextComposite composite, String textForParsing);

    private static class DefaultHandlerRequest extends AbstractChainHandler {
        private static DefaultHandlerRequest handler = new DefaultHandlerRequest();

        public DefaultHandlerRequest() {
        }

        public static DefaultHandlerRequest getHandlerRequest() {
            return handler;
        }

        @Override
        public void handleRequest(TextComposite composite, String textForParsing) {
        }

        @Override
        public void chain(TextComposite composite, String textForParsing) {
        }
    }
}
