package com.roboticsinc.robotinventory.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;
import java.util.Objects;

@Entity
@Table(name = "robot")
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    private Year yearBuilt;

    private float mass; // assume all robots have same mass unit

    private String color;

    @NotNull
    @OneToOne
    private RobotState state;

    /*
    TODO :: Add robot functions
    @OneToMany
    @JoinTable
    private Set<RobotFunction> functions;
     */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Year yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public RobotState getState() {
        return state;
    }

    public void setState(RobotState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return id.equals(robot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}