package main.web;

import main.entity.People;
import main.exception.GroupNotFoundException;
import main.exception.PersonNotFoundException;
import main.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/dekanat/people")
public class PeopleController {
    private PeopleService peopleService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public People addPerson(@RequestBody People newPerson) {
        return peopleService.addPerson(newPerson);
    }

    @GetMapping("/all")
    public ResponseEntity<List<People>> getAllPeople() {
        List<People> list = peopleService.listPeople();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<People> getPerson(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(peopleService.findPerson(id), HttpStatus.OK );
        } catch (GroupNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person no found");
        }
    }

    @GetMapping("/findByGroupAndType/{group}/{type}")
    public ResponseEntity<List<People>> getAllPeopleByGroupAndType(@PathVariable("group") Long groupId, @PathVariable("type") String type) {
        List<People> list = peopleService.findAllByGroupIdAndType(groupId, type);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findByType/{type}")
    public ResponseEntity<List<People>> getAllPeopleByType(@PathVariable("type") String type) {
        List<People> list = peopleService.findAllByType(type);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findBySubjectAndType/{subject}/{type}")
    public ResponseEntity<List<People>> getAllPeopleBySubjectAndType(@PathVariable("subject") String subject, @PathVariable("type") String type) {
        List<People> list = peopleService.findAllBySubjectIdAndType(subject, type);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePersonById(@PathVariable("id") long id) {
        try {
            peopleService.deleteById(id);
        } catch (PersonNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllPeople() {
        peopleService.deleteAll();
    }

    @Autowired
    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }
}
