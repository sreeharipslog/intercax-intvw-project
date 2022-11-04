package com.roboticsinc.robotinventory.repository;

import com.roboticsinc.robotinventory.domain.RobotState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RobotStateRepository extends JpaRepository<RobotState, Long> {

    Optional<RobotState> findByCode(String stateCode);
}