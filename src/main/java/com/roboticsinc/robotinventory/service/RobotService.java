package com.roboticsinc.robotinventory.service;

import com.roboticsinc.robotinventory.domain.Robot;
import com.roboticsinc.robotinventory.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RobotService {

    @Autowired
    private RobotRepository robotRepository;

    /**
     * Method to find robot by id
     *
     * @param id robot id
     * @return Robot object
     */
    public Optional<Robot> getRobotById(Long id) {
        return robotRepository.findById(id);
    }

    public Optional<List<Robot>> getAllDeployableRobots() {
        return null;
    }
}