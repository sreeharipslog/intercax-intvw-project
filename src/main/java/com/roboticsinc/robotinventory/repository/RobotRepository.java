package com.roboticsinc.robotinventory.repository;

import com.roboticsinc.robotinventory.domain.Robot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RobotRepository extends JpaRepository<Robot, Long> {
}