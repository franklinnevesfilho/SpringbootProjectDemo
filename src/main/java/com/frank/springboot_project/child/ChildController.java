package com.frank.springboot_project.child;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("child")
public class ChildController {
    private ChildService childService;

    @Autowired
    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping("list")
    public List<Child> getChildren() {
        return childService.getChildren();
    }

    @PostMapping
    public void addChild(@RequestBody Child child) {
        childService.addNewChild(child);
    }
    @DeleteMapping(path = "delete-{childId}")
    public void deleteChild(@PathVariable("childId") Long id){
        childService.deleteChild(id);
    }

    @PutMapping(path = "update-{childId}")
    public void updateChild(@PathVariable("childId")Long childId,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName){
        childService.updateChild(childId, firstName, lastName);
    }

}
