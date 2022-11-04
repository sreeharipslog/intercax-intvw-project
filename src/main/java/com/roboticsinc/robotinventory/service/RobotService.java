package com.roboticsinc.robotinventory.service;

import com.roboticsinc.robotinventory.domain.Robot;
import com.roboticsinc.robotinventory.domain.RobotState;
import com.roboticsinc.robotinventory.exception.BusinessException;
import com.roboticsinc.robotinventory.repository.RobotRepository;
import com.roboticsinc.robotinventory.repository.RobotStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.roboticsinc.robotinventory.constant.ErrorConstants.BusinessError.INVALID_ROBOT_STATE;

@Service
@Transactional
public class RobotService {

    @Autowired
    private RobotRepository robotRepository;

    @Autowired
    private RobotStateRepository robotStateRepository;

    public Optional<Robot> getRobotById(Long id) {
        return robotRepository.findById(id);
    }

    public List<Robot> getAllRobots() {
        return robotRepository.findAll();
    }

    public List<Robot> getAllRobotsByState(String stateCode) {
        RobotState state = getRobotStateByCode(stateCode);
        return robotRepository.findAllByState(state);
    }

    public void updateRobotState(Long id, String stateCode) {
        RobotState state = getRobotStateByCode(stateCode);
        robotRepository.updateRobotState(state);
    }

    private RobotState getRobotStateByCode(String code) {
        return robotStateRepository.findByCode(code).orElseThrow(
                () -> new BusinessException(INVALID_ROBOT_STATE.getErrorCode(), INVALID_ROBOT_STATE.getMessage()));
    }
}