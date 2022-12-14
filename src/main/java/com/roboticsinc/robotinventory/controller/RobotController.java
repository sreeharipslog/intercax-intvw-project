package com.roboticsinc.robotinventory.controller;

import com.roboticsinc.robotinventory.constant.AppConstants;
import com.roboticsinc.robotinventory.controller.dto.RobotDTO;
import com.roboticsinc.robotinventory.controller.mapper.InventoryMapper;
import com.roboticsinc.robotinventory.domain.Robot;
import com.roboticsinc.robotinventory.exception.BusinessException;
import com.roboticsinc.robotinventory.service.RobotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.roboticsinc.robotinventory.constant.ErrorConstants.BusinessError.INVENTORY_EMPTY;
import static com.roboticsinc.robotinventory.constant.ErrorConstants.BusinessError.ROBOT_NOT_FOUND;
import static com.roboticsinc.robotinventory.constant.ErrorConstants.ErrorMessages;

/**
 * Robot controller for Robot Inventory
 *
 * @author sreeharipslog
 */
@RestController
@RequestMapping("/robots")
public class RobotController {

    private static final Logger logger = LoggerFactory.getLogger(RobotController.class);

    @Autowired
    private RobotService robotService;

    @Autowired
    private InventoryMapper<Robot, RobotDTO> robotMapper;

    /**
     * Adds new Robot
     *
     * @param robotDTO robot dto
     * @return saved {@link Robot} id
     */
    @PostMapping
    public String createRobot(@RequestBody @Valid RobotDTO robotDTO) {
        Long id = robotService.saveRobot(robotMapper.dtoToDomain(robotDTO));
        return "Robot created successfully with id = " + id;
    }

    /**
     * Query/Track robot inventory. If state is not passed, returns all active (not end-of-life) robots.
     *
     * @param state robot state
     * @return response with count
     */
    @GetMapping
    public Map<String, Object> getRobots(@RequestParam(value = "state", required = false) String state) {
        List<Robot> robots;
        if (Objects.isNull(state)) robots = robotService.getAllRobots();
        else {
            logger.info("Fetching robots by state = {}", state);
            robots = robotService.getAllRobotsByState(state);
        }
        if (robots.isEmpty()) throw new BusinessException(INVENTORY_EMPTY.getErrorCode(), INVENTORY_EMPTY.getMessage());
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", robots.size());
        response.put("data", robotMapper.domainListToDtoList(robots));
        return response;
    }

    /**
     * Get Robot by ID
     *
     * @param id robot id
     * @return robot details
     * @throws BusinessException when robot is not found in inventory
     */
    @GetMapping("/{id}")
    public RobotDTO getRobot(@PathVariable("id") @NotNull(message = ErrorMessages.INVALID_REQUEST) Long id) {
        Robot robot = robotService.getRobotById(id)
                .orElseThrow(() -> new BusinessException(ROBOT_NOT_FOUND.getErrorCode(), ROBOT_NOT_FOUND.getMessage()));
        return robotMapper.domainToDto(robot);
    }

    /**
     * Update robot using id
     *
     * @param id Robot id
     * @return message
     */
    @PutMapping("/{id}")
    public String updateRobot(@RequestBody @Valid RobotDTO robotDTO, @PathVariable("id") @NotNull(message = ErrorMessages.INVALID_REQUEST) Long id) {
        robotService.updateRobot(id, robotDTO);
        return "Robot updated successfully";
    }

    /**
     * Soft delete Robot. i.e. marks robots state as End-of-Life
     *
     * @param id robot id
     * @return message
     */
    @DeleteMapping("/{id}")
    public String deleteRobot(@PathVariable("id") @NotNull(message = ErrorMessages.INVALID_REQUEST) Long id) {
        // Challenge mention - Soft deletion, Hence, Robot entity is persisted with decommissioned state.
        robotService.updateRobotState(id, AppConstants.END_OF_LIFE);
        return "Robot deleted successfully";
    }
}