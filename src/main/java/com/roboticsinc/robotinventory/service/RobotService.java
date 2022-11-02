package com.roboticsinc.robotinventory.service;

import com.roboticsinc.robotinventory.domain.Robot;
import com.roboticsinc.robotinventory.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Robot getRobotById(Long id) {
        return robotRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}