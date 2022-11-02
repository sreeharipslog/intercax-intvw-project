package com.roboticsinc.robotinventory.controller;

import com.roboticsinc.robotinventory.controller.dto.RobotDTO;
import com.roboticsinc.robotinventory.domain.Robot;
import com.roboticsinc.robotinventory.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("v1.0/robots")
public class RobotController {

    @Autowired
    private RobotService robotService;

    @PostMapping()
    public Long createRobot(RobotDTO robotDTO) {
        // TODO :: mapper + service method to convert and save robot
        return null;
    }

    @GetMapping("/{id}")
    public Robot getRobot(@RequestParam("id") @NotNull(message = "invalid robot id") Long id) {
        return robotService.getRobotById(id);
    }

    @PutMapping
    public Robot updateRobot() {
        return null;
    }

    @DeleteMapping("/{id}")
    public Long decommissionRobot(@RequestParam("id") @NotNull(message = "invalid robot id") Long id) {
        return null;
    }
}