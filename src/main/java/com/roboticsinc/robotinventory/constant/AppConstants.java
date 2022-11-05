package com.roboticsinc.robotinventory.constant;

public interface AppConstants {

    /**
     * Robot state.<br>
     * <ul>
     *     <li>AWAITED : Currently being created</li>
     *     <li>READY : Tested and Ready to ship</li>
     *     <li>DEPLOYED : Deployed on field</li>
     *     <li>END_OF_LIFE : Damaged beyond repair</li>
     * </ul>
     */
    enum State {
        AWAITED, READY, DEPLOYED, END_OF_LIFE;
    }

    // Regex constants
    String ROBOT_STATE_REGEX = "Awaited|Ready|Deployed|Decommissioned";
}