package main.web;

import main.entity.Groups;
import main.exception.GroupNotFoundException;
import main.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/dekanat/groups")
public class GroupsController {
    private GroupService groupsService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Groups addGroup(@RequestBody Groups newGroup) {
        return groupsService.addGroup(newGroup);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Groups>> getAllGroups() {
        List<Groups> list = groupsService.listGroups();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Groups> getGroup(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(groupsService.findGroup(id), HttpStatus.OK );
        } catch (GroupNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group no found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGroupById(@PathVariable("id") long id) {
        try {
            groupsService.deleteById(id);
        } catch (GroupNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such group");
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllGroups() {
        groupsService.deleteAll();
    }

    @Autowired
    public void setGroupService(GroupService groupsService) {
        this.groupsService = groupsService;
    }
}
