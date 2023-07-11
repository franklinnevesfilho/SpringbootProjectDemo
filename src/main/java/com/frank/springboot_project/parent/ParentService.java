package com.frank.springboot_project.parent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentService {
    private final ParentRepository parentRepo;

    @Autowired
    public ParentService(ParentRepository parentRepo) {
        this.parentRepo = parentRepo;
    }

    public List<Parent> getParents() {
        return parentRepo.findAll();
    }

    public Parent getParent(Long id){
        return parentRepo.findById(id).orElseThrow(()-> new IllegalStateException("Parent by id:" + id + " Does Not Exist"));
    }

    public void addParent(Parent parent) {
        parentRepo.save(parent);
    }

    public void deleteParent(Long id) {
        boolean exists = parentRepo.existsById(id);
        if (exists) parentRepo.deleteById(id);
        else throw new IllegalStateException("Parent by id:" + id + " Does Not Exist");
    }

    public void updateParent(Parent parent){
        parentRepo.save(parent);
    }
    public void updateParent(Long id, String firstName, String lastName, String address, String phone){
        Parent parent = parentRepo.findById(id).orElseThrow(() -> new IllegalStateException("Parent by id:" + id + " Does Not Exist"));
        if(firstName != null &&
                firstName.length() > 0 &&
                !firstName.equalsIgnoreCase(parent.getFirstName())) parent.setFirstName(firstName);
        if(lastName != null &&
                lastName.length() > 0 &&
                !lastName.equalsIgnoreCase(parent.getLastName())) parent.setLastName(lastName);
        if(address != null &&
                address.length() > 0 &&
                !address.equalsIgnoreCase(parent.getAddress())) parent.setAddress(address);
        if(phone != null &&
                phone.length() == 10 &&
                !phone.equalsIgnoreCase(parent.getPhone()) && phone.matches("\\d+")) parent.setPhone(phone);
    }

}
