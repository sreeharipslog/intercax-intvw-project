package com.roboticsinc.robotinventory.service;

import com.roboticsinc.robotinventory.domain.Robot;
import com.roboticsinc.robotinventory.domain.RobotFunction;
import com.roboticsinc.robotinventory.domain.RobotState;
import com.roboticsinc.robotinventory.exception.BusinessException;
import com.roboticsinc.robotinventory.repository.RobotFunctionRepository;
import com.roboticsinc.robotinventory.repository.RobotRepository;
import com.roboticsinc.robotinventory.repository.RobotStateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.roboticsinc.robotinventory.constant.ErrorConstants.BusinessError.INVALID_ROBOT_FUNCTION;
import static com.roboticsinc.robotinventory.constant.ErrorConstants.BusinessError.INVALID_ROBOT_STATE;

/**
 * Robot service
 *
 * @author sreeharipslog
 */
@Service
@Transactional
public class RobotService {

    /*
    For better abstraction and to support multiple implementations create interface for service and implements it for
    this class
     */

    private static final Logger logger = LoggerFactory.getLogger(RobotService.class);

    @Autowired
    private RobotRepository robotRepository;

    @Autowired
    private RobotStateRepository robotStateRepository;

    @Autowired
    private RobotFunctionRepository robotFunctionRepository;

    /**
     * Method to fetch robot by id
     *
     * @param id robot id
     * @return {@link Robot} instance
     */
    public Optional<Robot> getRobotById(Long id) {
        return robotRepository.findById(id);
    }

    /**
     * Method to fetch all robots
     *
     * @return list of {@link Robot} instance
     */
    public List<Robot> getAllRobots() {
        return robotRepository.findAll();
    }

    /**
     * Method to fetch all robots by given state code
     *
     * @param stateCode robot state code
     * @return list of {@link Robot} instance
     */
    public List<Robot> getAllRobotsByState(String stateCode) {
        RobotState state = getRobotStateByCode(stateCode);
        return robotRepository.findAllByState(state);
    }

    /**
     * Method to update robot status
     *
     * @param id        robot id
     * @param stateCode robot status code
     */
    public void updateRobotState(Long id, String stateCode) {
        RobotState state = getRobotStateByCode(stateCode);
        robotRepository.updateRobotState(id, state);
        logger.info("Robot (id = {}) status updated to {}", id, stateCode);
    }

    public RobotState getRobotStateByCode(String code) {
        return robotStateRepository.findByCode(code).orElseThrow(
                () -> new BusinessException(INVALID_ROBOT_STATE.getErrorCode(), INVALID_ROBOT_STATE.getMessage()));
    }

    public RobotFunction getRobotFunctionByCode(String code) {
        return robotFunctionRepository.findByCode(code).orElseThrow(
                () -> new BusinessException(INVALID_ROBOT_FUNCTION.getErrorCode(),
                        INVALID_ROBOT_FUNCTION.getMessage()));
    }

    /**
     * Transactional Method to save robot entity to persistence
     *
     * @param robot data to be saved
     * @return id of persisted robot
     */
    public Long saveRobot(Robot robot) {
        robot = robotRepository.save(robot);
        return robot.getId();
    }
}