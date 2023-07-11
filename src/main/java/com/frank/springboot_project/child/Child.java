package com.frank.springboot_project.child;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frank.springboot_project.parent.Parent;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "Child")
@Table(name = "Child")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="id",
            nullable = false
    )
    private Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "parentsChildren")
    private Set<Parent> parents = new HashSet<>();

    @Column(name="first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(name="last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(name="dob",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate dob;

    @Transient
    private Integer age;

    public Child() {}

    public Child(Long id, String firstName, String lastName, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public Child(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Set<Parent> getParents(){
        return this.parents;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
