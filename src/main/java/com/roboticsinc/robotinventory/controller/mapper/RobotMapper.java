package com.roboticsinc.robotinventory.controller.mapper;

import com.roboticsinc.robotinventory.controller.dto.RobotDTO;
import com.roboticsinc.robotinventory.domain.Robot;
import com.roboticsinc.robotinventory.domain.RobotFunction;
import com.roboticsinc.robotinventory.service.RobotService;
import com.roboticsinc.robotinventory.util.InventoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component("robotMapper")
public class RobotMapper implements InventoryMapper<Robot, RobotDTO> {

    @Autowired
    private RobotService robotService;

    @Override
    public Robot dtoToDomain(RobotDTO dto) {
        Robot robot = new Robot();
        robot.setName(dto.getName());
        robot.setMass(dto.getMass());
        robot.setYearBuilt(InventoryUtils.isValidYear(dto.getYearBuilt()));
        robot.setColor(dto.getColor());
        robot.setState(robotService.getRobotStateByCode(dto.getState()));
        dto.getFunctions().stream().map(robotService::getRobotFunctionByCode).forEach(robot::addRobotFunction);
        return robot;
    }

    @Override
    public RobotDTO domainToDto(Robot domain) {
        RobotDTO robotDTO = new RobotDTO();
        robotDTO.setName(domain.getName());
        robotDTO.setMass(domain.getMass());
        robotDTO.setYearBuilt(domain.getYearBuilt());
        robotDTO.setColor(domain.getColor());
        robotDTO.setState(domain.getState().getCode());
        robotDTO.setFunctions(domain.getFunctions().stream().map(RobotFunction::getCode).collect(Collectors.toList()));
        return robotDTO;
    }
}