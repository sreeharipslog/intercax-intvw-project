package com.roboticsinc.robotinventory.domain;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "robot_function")
public class RobotFunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false)
    private String code;

    private String description;

    @ManyToMany(mappedBy = "functions")
    private Set<Robot> robots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Robot> getRobots() {
        // Once initialized (or lazy-loaded) no need to fetch again from persistence, avoids additional query
        // JOIN FETCH is needed to load robots via function
        return Hibernate.isInitialized(robots) ? robots : new HashSet<>();
    }

    public void setRobots(Set<Robot> robots) {
        this.robots = robots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RobotFunction that = (RobotFunction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}