package com.roboticsinc.robotinventory.constant;

public interface AppConstants {

    enum Color {
        YELLOW, BLUE, GREEN, BLACK;
    }

    enum State {
        AWAITED, READY, DEPLOYED, DECOMMISSIONED;
    }

    // Regex constants
    String ROBOT_STATE_REGEX = "Awaited|Ready|Deployed|Decommissioned";
}