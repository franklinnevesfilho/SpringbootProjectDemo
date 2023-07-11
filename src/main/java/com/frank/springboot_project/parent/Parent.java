package com.frank.springboot_project.parent;

import com.frank.springboot_project.child.Child;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "Parent")
@Table(name="Parent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "parents_children",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private Set<Child> parentsChildren = new HashSet<>();

    @Column(name="first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String first_name;
    @Column(name="last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String last_name;
    @Column(name="dob",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate dob;
    @Column(name="address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String address;
    @Column(name="phone",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String phone;
    @Transient
    private Integer age;

    public Parent() {}

    public Parent(Long id, String first_name, String last_name, LocalDate dob, String address, String phone, Integer age) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.age = age;
    }

    public Parent(String first_name, String last_name, LocalDate dob, String address, String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Child> getParentsChildren(){
        return this.parentsChildren;
    }
    public void addChild(Child child){
        this.parentsChildren.add(child);
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
