package com.roboticsinc.robotinventory.controller;

import com.roboticsinc.robotinventory.constant.AppConstants;
import com.roboticsinc.robotinventory.constant.ErrorConstants;
import com.roboticsinc.robotinventory.controller.dto.RobotDTO;
import com.roboticsinc.robotinventory.domain.Robot;
import com.roboticsinc.robotinventory.exception.BusinessException;
import com.roboticsinc.robotinventory.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Long createRobot(@RequestBody RobotDTO robotDTO) {
        // TODO :: mapper + service method to convert and save robot
        return null;
    }

    @GetMapping
    public List<Robot> getRobots(@RequestParam(value = "state", required = false) @Pattern(regexp =
            AppConstants.ROBOT_STATE_REGEX) String state) {
        if (Objects.isNull(state)) return Optional.of(robotService.getAllRobots())
                .orElseThrow(() -> new BusinessException(INVENTORY_EMPTY.getErrorCode(), INVENTORY_EMPTY.getMessage()));
        else {
            return Optional.of(robotService.getAllRobotsByState(state)).orElseThrow(
                    () -> new BusinessException(INVENTORY_EMPTY.getErrorCode(), INVENTORY_EMPTY.getMessage()));
        }
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
    public String deleteRobot(@PathVariable("id") @NotNull(message = ErrorConstants.INVALID_REQUEST) Long id) {
        robotService.updateRobotState(id, AppConstants.State.DECOMMISSIONED.name());
        return "Robot deleted successfully";
    }
}