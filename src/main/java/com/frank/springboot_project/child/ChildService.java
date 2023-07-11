package com.frank.springboot_project.child;

import com.frank.springboot_project.child.Child;
import com.frank.springboot_project.child.ChildRepository;
import com.frank.springboot_project.parent.Parent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ChildService {
    private final ChildRepository childRepo;

    @Autowired
    public ChildService(ChildRepository childRepo) {
        this.childRepo = childRepo;
    }
    public List<Child> getStudents(){
        return childRepo.findAll();
    }

    public List<Child> getChildren(){
        return childRepo.findAll();
    }

    public Child getChild(Long id){
        return childRepo.findById(id).orElseThrow(()-> new IllegalStateException("Child by id:" + id + " Does Not Exist"));
    }
    public void addNewChild(Child child) {
        childRepo.save(child);
    }

    public void deleteChild(Long id) {
        boolean exists = childRepo.existsById(id);
        if (exists)childRepo.deleteById(id);
        else throw new IllegalStateException("Parent by id:" + id + " Does not exist");
    }
    @Transactional
    public void updateChild(Long childId, String firstName, String lastName) {
        Child child = childRepo.findById(childId).orElseThrow(() -> new IllegalStateException("Student with id: " + childId + " not found"));
        if (firstName != null && firstName.length() > 0 && !firstName.equals(child.getFirstName())) {
            child.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !lastName.equals(child.getLastName())) {
            child.setLastName(lastName);
        }
    }
}
