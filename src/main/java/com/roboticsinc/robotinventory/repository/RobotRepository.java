package com.roboticsinc.robotinventory.repository;

import com.roboticsinc.robotinventory.domain.Robot;
import com.roboticsinc.robotinventory.domain.RobotState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RobotRepository extends JpaRepository<Robot, Long> {

    List<Robot> findAllByState(RobotState state);

    @Modifying
    @Query("UPDATE Robot r SET r.state = :state where r.id = :id")
    void updateRobotState(@Param("id") Long id, @Param("state") RobotState state);

    List<Robot> findAllByStateNot(RobotState state);
}