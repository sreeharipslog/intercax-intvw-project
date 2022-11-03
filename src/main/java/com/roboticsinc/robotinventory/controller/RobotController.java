package com.roboticsinc.robotinventory.controller;

import com.roboticsinc.robotinventory.constant.ErrorConstants;
import com.roboticsinc.robotinventory.controller.dto.RobotDTO;
import com.roboticsinc.robotinventory.domain.Robot;
import com.roboticsinc.robotinventory.exception.BusinessException;
import com.roboticsinc.robotinventory.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.roboticsinc.robotinventory.constant.ErrorConstants.BusinessError.INVENTORY_EMPTY;
import static com.roboticsinc.robotinventory.constant.ErrorConstants.BusinessError.ROBOT_NOT_FOUND;

/**
 * Robot controller for Robot Inventory
 *
 * @author sreeharipslog
 */
@RestController
@RequestMapping("/robots")
public class RobotController {

    @Autowired
    private RobotService robotService;

    @PostMapping
    public Long createRobot(RobotDTO robotDTO) {
        // TODO :: mapper + service method to convert and save robot
        return null;
    }

    @GetMapping
    public List<Robot> getRobots() {
        return robotService.getAllDeployableRobots()
                .orElseThrow(() -> new BusinessException(INVENTORY_EMPTY.getErrorCode(), INVENTORY_EMPTY.getMessage()));
    }

    @GetMapping("/{id}")
    public Robot getRobot(@PathVariable("id") @NotNull(message = ErrorConstants.INVALID_REQUEST) Long id) {
        return robotService.getRobotById(id)
                .orElseThrow(() -> new BusinessException(ROBOT_NOT_FOUND.getErrorCode(), ROBOT_NOT_FOUND.getMessage()));
    }

    @PutMapping
    public Robot updateRobot() {
        return null;
    }

    @DeleteMapping("/{id}")
    public Long decommissionRobot(@PathVariable("id") @NotNull(message = ErrorConstants.INVALID_REQUEST) Long id) {
        return null;
    }
}