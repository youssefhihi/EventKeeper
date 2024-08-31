package com.EventKeeper;
import com.EventKeeper.UI.EventsUI;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        EventsUI.run();
    }
}