package eu.xycorp;

import java.util.logging.Logger;

import javafx.application.Application;

public final class Main {

    private static final Logger LOGGER = Logger.getLogger("open-universe");

    public static void main(final String[] args) {
        Application.launch(Universe.class, args);
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}