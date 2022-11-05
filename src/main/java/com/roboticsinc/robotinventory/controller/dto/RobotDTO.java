package com.roboticsinc.robotinventory.controller.dto;

import com.roboticsinc.robotinventory.constant.AppConstants;
import com.roboticsinc.robotinventory.constant.ErrorConstants.ErrorMessages;

import javax.validation.constraints.Pattern;
import java.util.List;

public class RobotDTO {
    private String name;
    private float mass;
    private String yearBuilt;
    private String color;
    @Pattern(regexp = AppConstants.ROBOT_STATE_REGEX, message = ErrorMessages.INVALID_ROBOT_STATE)
    private String state;

    List<@Pattern(regexp = AppConstants.ROBOT_FUNCTION_REGEX,
                  message = ErrorMessages.INVALID_ROBOT_FUNCTION) String> functions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(String yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public List<String> getFunctions() {
        return functions;
    }

    public void setFunctions(List<String> functions) {
        this.functions = functions;
    }
}