package main.web;

import main.entity.Marks;
import main.exception.GroupNotFoundException;
import main.exception.MarkNotFoundException;
import main.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/dekanat/marks")
public class MarksController {
    private MarksService marksService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Marks addMark(@RequestBody Marks newMark) {
        return marksService.addMark(newMark);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Marks>> getAllMarks() {
        List<Marks> list = marksService.listMarks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Marks> getMark(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(marksService.findMark(id), HttpStatus.OK );
        } catch (GroupNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mark no found");
        }
    }

    @GetMapping("/findByStudent/{student}")
    public ResponseEntity<List<Marks>> getAllMarksByStudentId(@PathVariable("student") Long studentId) {
        List<Marks> list = marksService.findAllByStudentId(studentId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findByStudentAndSubject/{student}/{subject}")
    public ResponseEntity<List<Marks>> getAllMarksByStudentIdAndSubject(@PathVariable("student") Long studentId, @PathVariable("subject") String subject) {
        List<Marks> list = marksService.findAllByStudentIdAndSubject(studentId, subject);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findByGroupAndSubject/{group}/{subject}")
    public ResponseEntity<List<Marks>> getAllMarksByGroupIdAndSubject(@PathVariable("group") Long groupId, @PathVariable("subject") String subject) {
        List<Marks> list = marksService.findAllByGroupIdAndSubject(groupId, subject);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findByTeacherAndSubject/{teacher}/{subject}")
    public ResponseEntity<List<Marks>> getAllMarksByTeacherIdAndSubject(@PathVariable("teacher") Long teacherId, @PathVariable("subject") String subject) {
        List<Marks> list = marksService.findAllByTeacherIdAndSubject(teacherId, subject);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public void changeMark(@PathVariable("id") Long id, @RequestParam int newMark) {
        try {
            marksService.setValueForId(id, newMark);
        } catch (MarkNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such mark");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMarkById(@PathVariable("id") long id) {
        try {
            marksService.deleteById(id);
        } catch (MarkNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such mark");
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllMarks() {
        marksService.deleteAll();
    }

    @Autowired
    public void setMarksService(MarksService marksService) {
        this.marksService = marksService;
    }
}
