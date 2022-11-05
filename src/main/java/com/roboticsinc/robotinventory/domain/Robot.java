package com.roboticsinc.robotinventory.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "robot")
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String yearBuilt;

    private float mass; // assume all robots have same mass unit

    private String color;

    @NotNull
    @OneToOne
    private RobotState state;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "robot_function_mapping",
               joinColumns = @JoinColumn(name = "robot_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "function_id", referencedColumnName = "id"))
    private Set<RobotFunction> functions = new HashSet<>();


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

    public String getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(String yearBuilt) {
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

    public Set<RobotFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(Set<RobotFunction> functions) {
        this.functions = functions;
    }

    /**
     * Utility to add robot function such that bidirectional relationship consistency is maintained
     *
     * @param fun robot function
     */
    public void addRobotFunction(RobotFunction fun) {
        functions.add(fun);
        fun.getRobots().add(this);
    }

    /**
     * Utility to remove robot function such that bidirectional relationship consistency is maintained
     *
     * @param fun robot function
     */
    public void removeRobotFunction(RobotFunction fun) {
        functions.remove(fun);
        fun.getRobots().remove(this);
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