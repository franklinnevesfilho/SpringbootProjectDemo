package com.frank.springboot_project.parent;

import com.frank.springboot_project.child.Child;
import com.frank.springboot_project.child.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="parent")
public class ParentController {
    private ParentService parentService;
    private ChildService childService;

    @Autowired
    public ParentController(ParentService parentService, ChildService childService) {
        this.parentService = parentService;
        this.childService = childService;
    }
    @GetMapping("list")
    public List<Parent> getParents(){
        return parentService.getParents();
    }

    @PostMapping
    public void addParent(@RequestBody Parent parent){
        parentService.addParent(parent);
    }

    @DeleteMapping(path = "delete-{parentId}")
    public void deleteParent(@PathVariable("parentId") Long id){
        parentService.deleteParent(id);
    }



    @PutMapping("{parentId}/children/{childId}")
    public void addChildToParent(@PathVariable("parentId")Long parentId, @PathVariable("childId")Long childId){
        Parent parent = parentService.getParent(parentId);
        Child child = childService.getChild(childId);
        parent.addChild(child);
        parentService.updateParent(parent);
    }

    @PutMapping(path = "update-{parentId}")
    public void updateParent(@PathVariable("parentId")Long id,
                             @RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) String address,
                             @RequestParam(required = false) String phone
                             ){
        parentService.updateParent(id, firstName, lastName, address, phone);
    }
}
