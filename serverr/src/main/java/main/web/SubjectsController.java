package main.web;

import main.entity.Subjects;
import main.exception.GroupNotFoundException;
import main.exception.SubjectNotFoundException;
import main.service.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/dekanat/subjects")
public class SubjectsController {
    private SubjectsService subjectsService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Subjects addSubject(@RequestBody Subjects newSubject) {
        return subjectsService.addSubject(newSubject);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subjects>> getAllSubjects() {
        List<Subjects> list = subjectsService.listSubjects();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Subjects> getSubject(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(subjectsService.findSubject(id), HttpStatus.OK );
        } catch (GroupNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject no found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSubjectById(@PathVariable("id") long id) {
        try {
            subjectsService.deleteById(id);
        } catch (SubjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such subject");
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllSubjects() {
        subjectsService.deleteAll();
    }

    @Autowired
    public void setSubjectsService(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }
}
