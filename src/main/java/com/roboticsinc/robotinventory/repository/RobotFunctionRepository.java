package com.roboticsinc.robotinventory.repository;

import com.roboticsinc.robotinventory.domain.RobotFunction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RobotFunctionRepository extends JpaRepository<RobotFunction, Long> {

    Optional<RobotFunction> findByCode(String functionCode);
}