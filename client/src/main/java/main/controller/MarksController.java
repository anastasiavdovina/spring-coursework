package main.controller;

import main.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static main.utility.Utils.processGetRequest;
import static main.utility.Utils.processPostRequest;

@Controller
public class MarksController {
    private User user;

    @GetMapping( "/marks/add")
    public String addMark(Model model) {
        model.addAttribute("title", "Add mark");

        String subjectsURL = "http://localhost:8080/dekanat/subjects/all";
        List<Subjects> subjects = processGetRequest(subjectsURL, List.class);
        model.addAttribute("subjects", subjects);
        String teachersURL = "http://localhost:8080/dekanat/people/findByType/teacher";
        List<People> teachers = processGetRequest(teachersURL, List.class);
        model.addAttribute("teachers", teachers);
        String studentsURL = "http://localhost:8080/dekanat/people/findByType/student";
        List<People> students = processGetRequest(studentsURL, List.class);
        model.addAttribute("students", students);
        List<String> marks = new ArrayList<String>();
        marks.add("2");
        marks.add("3");
        marks.add("4");
        marks.add("5");
        model.addAttribute("marks", marks);
        return "marks/addMark";
    }

    @PostMapping("/marks/add")
    public String addPeople(@RequestParam long studentId, @RequestParam long subjectId, @RequestParam long teacherId,
                            @RequestParam String value) {
        String subjectURL = "http://localhost:8080/dekanat/subjects/" + subjectId;
        Subjects subject = processGetRequest(subjectURL, Subjects.class);

        String teachertURL = "http://localhost:8080/dekanat/people/" + teacherId;
        People teacher = processGetRequest(teachertURL, People.class);

        String studentURL = "http://localhost:8080/dekanat/people/" + studentId;
        People student = processGetRequest(studentURL, People.class);

        String url = "http://localhost:8080/dekanat/marks/add";
        String json = "{\n" + "\"value\":" + Integer.parseInt(value) + ",\n" + "\"studentId\":" + "{\n" +
        "\"id\":" + student.getId() +  ",\n" +
        "\"firstName\":" + "\"" + student.getFirstName() + "\",\n" +
        "\"lastName\":" + "\"" + student.getLastName() + "\",\n" +
        "\"fatherName\":" + "\"" + student.getFatherName() + "\",\n" +
        "\"type\":" + "\"" + student.getType() + "\",\n" +
        "\"groupId\":" + "{\n" +
            "\"id\":" + student.getGroupId().getId() + ",\n" +
            "\"name\":" + "\"" + student.getGroupId().getName() +
                    "\"\n}\n" +
            "}" + ",\n" +
            "\"teacherId\":" + "{\n" +
            "\"id\":" + teacher.getId() + ",\n" +
            "\"firstName\":" + "\"" + teacher.getFirstName() + "\",\n" +
            "\"lastName\":" + "\"" + teacher.getLastName() + "\",\n" +
            "\"fatherName\":" + "\"" + teacher.getFatherName() + "\",\n" +
            "\"type\":" + "\"" + teacher.getType() + "\",\n" +
            "\"groupId\":" + "{\n" +
                "\"id\":" + teacher.getGroupId().getId() + ",\n" +
                "\"name\":" + "\"" + teacher.getGroupId().getName() +
                        "\"\n}\n" +
                "}" + ",\n" +
                "\"subjectId\":" + "{\n" +
                "\"id\":" + subject.getId() + ",\n" +
                "\"name\":" + "\"" + subject.getName() +
                        "\"\n}\n" +
                "}";

        processPostRequest(url, user.getToken(), json, HttpMethod.POST, MediaType.APPLICATION_JSON);
        return "redirect:/marks";
    }

    @GetMapping("/marks")
    public String getAllMarks(Model model) {
        model.addAttribute("title", "Marks");

        String url = "http://localhost:8080/dekanat/marks/all";
        List<Marks> marks = processGetRequest(url, List.class);
        model.addAttribute("marks", marks);

        return "marks/allMarks";
    }

    @GetMapping("/marks/findByStudent")
    public String getAllMarksByStudentId(Model model) {
        model.addAttribute("title", "Find by student");
        String studentsURL = "http://localhost:8080/dekanat/people/findByType/student";
        List<People> peopleList = processGetRequest(studentsURL, List.class);
        model.addAttribute("students", peopleList);

        return "marks/student";
    }

    @GetMapping("/marks/findByStudentRes")
    public String getAllMarksByStudentId(@RequestParam long student, Model model) {
        model.addAttribute("title", "Find by student");

        String studentsURL = "http://localhost:8080/dekanat/people/" + student;
        People studentTemp = processGetRequest(studentsURL, People.class);
        model.addAttribute("student", studentTemp);

        String url = "http://localhost:8080/dekanat/marks/findByStudent/" + student;
        List<Marks> marksList = processGetRequest(url, List.class);
        model.addAttribute("marks", marksList);

        return "marks/studentRes";
    }

    @GetMapping("/marks/findByStudentAndSubject")
    public String getAllMarksByStudentIdAndSubject(Model model) {
        model.addAttribute("title", "Find by student and subject");
        String studentsURL = "http://localhost:8080/dekanat/people/findByType/student";
        List<People> peopleList = processGetRequest(studentsURL, List.class);
        model.addAttribute("students", peopleList);

        String subjectsURL = "http://localhost:8080/dekanat/subjects/all";
        List<Subjects> subjectsList = processGetRequest(subjectsURL, List.class);
        model.addAttribute("subjects", subjectsList);

        return "marks/studentAndSubject";
    }

    @GetMapping("/marks/findByStudentAndSubjectRes")
    public String getAllMarksByStudentIdAndSubject(@RequestParam long student, @RequestParam long subject, Model model) {
        model.addAttribute("title", "Find by student and subject");

        String studentsURL = "http://localhost:8080/dekanat/people/" + student;
        People studentTemp = processGetRequest(studentsURL, People.class);
        model.addAttribute("student", studentTemp);

        String subjectsURL = "http://localhost:8080/dekanat/subjects/" + subject;
        Subjects subjectTemp = processGetRequest(subjectsURL, Subjects.class);
        model.addAttribute("subject", subjectTemp);

        String url = "http://localhost:8080/dekanat/marks/findByStudentAndSubject/" + student + "/" + subjectTemp.getName();
        List<Marks> marksList = processGetRequest(url, List.class);
        model.addAttribute("marks", marksList);

        return "marks/studentAndSubjectRes";
    }

    @GetMapping("/marks/findByGroupAndSubject")
    public String getAllMarksByGroupIdAndSubject(Model model) {
        model.addAttribute("title", "Find by group and subject");
        String groupsURL = "http://localhost:8080/dekanat/groups/all";
        List<Groups> groupsList = processGetRequest(groupsURL, List.class);
        model.addAttribute("groups", groupsList);

        String subjectsURL = "http://localhost:8080/dekanat/subjects/all";
        List<Subjects> subjectsList = processGetRequest(subjectsURL, List.class);
        model.addAttribute("subjects", subjectsList);

        return "marks/groupAndSubject";
    }

    @GetMapping("/marks/findByGroupAndSubjectRes")
    public String getAllMarksByGroupIdAndSubject(@RequestParam long group, @RequestParam long subject, Model model) {
        model.addAttribute("title", "Find by group and subject");

        String groupsURL = "http://localhost:8080/dekanat/groups/" + group;
        Groups groupTemp = processGetRequest(groupsURL, Groups.class);
        model.addAttribute("group", groupTemp);

        String subjectsURL = "http://localhost:8080/dekanat/subjects/" + subject;
        Subjects subjectTemp = processGetRequest(subjectsURL, Subjects.class);
        model.addAttribute("subject", subjectTemp);

        String url = "http://localhost:8080/dekanat/marks/findByGroupAndSubject/" + group + "/" + subjectTemp.getName();
        List<Marks> marksList = processGetRequest(url, List.class);
        model.addAttribute("marks", marksList);
        return "marks/groupAndSubjectRes";
    }

    @GetMapping("/marks/findByTeacherAndSubject")
    public String getAllMarksByTeacherIdAndSubject(Model model) {
        model.addAttribute("title", "Find by teacher and subject");
        String teachersURL = "http://localhost:8080/dekanat/people/findByType/teacher";
        List<People> peopleList = processGetRequest(teachersURL, List.class);
        model.addAttribute("teachers", peopleList);

        String subjectsURL = "http://localhost:8080/dekanat/subjects/all";
        List<Subjects> subjectsList = processGetRequest(subjectsURL, List.class);
        model.addAttribute("subjects", subjectsList);

        return "marks/teacherAndSubject";
    }

    @GetMapping("/marks/findByTeacherAndSubjectRes")
    public String getAllMarksByTeacherIdAndSubject(@RequestParam long teacher, @RequestParam long subject, Model model) {
        model.addAttribute("title", "Find by teacher and subject");

        String teachersURL = "http://localhost:8080/dekanat/people/" + teacher;
        People teacherTemp = processGetRequest(teachersURL, People.class);
        model.addAttribute("teacher", teacherTemp);

        String subjectsURL = "http://localhost:8080/dekanat/subjects/" + subject;
        Subjects subjectTemp = processGetRequest(subjectsURL, Subjects.class);
        model.addAttribute("subject", subjectTemp);

        String url = "http://localhost:8080/dekanat/marks/findByTeacherAndSubject/" + teacher + "/" + subjectTemp.getName();
        List<Marks> marksList = processGetRequest(url, List.class);
        model.addAttribute("marks", marksList);

        return "marks/teacherAndSubjectRes";
    }


    @GetMapping("/marks/update/{id}")
    public String changeMark(@PathVariable("id") long id, Model model) {
        model.addAttribute("title", "Edit mark");

        String url = "http://localhost:8080/dekanat/marks/" + id;
        Marks mark = processGetRequest(url, Marks.class);
        model.addAttribute("mark", mark);

        List<String> marks = new ArrayList<String>();
        marks.add("2");
        marks.add("3");
        marks.add("4");
        marks.add("5");
        model.addAttribute("allMarks", marks);

        return "marks/edit";
    }

    @PostMapping("/marks/update/{id}")
    public String changeMark(@PathVariable("id") long id, @RequestParam int value) {
        String url = "http://localhost:8080/dekanat/marks/update/" + id + "?newMark=" + value;

        processPostRequest(url, user.getToken(), null, HttpMethod.PUT, null);


        return "redirect:/marks";
    }

    @PostMapping("/marks/delete/{id}")
    public String deleteMarkById(@PathVariable("id") long id) {
        String url = "http://localhost:8080/dekanat/marks/delete/" + id;

        processPostRequest(url, user.getToken(), null, HttpMethod.DELETE, null);
        return "redirect:/marks";
    }

    @PostMapping("/marks/deleteAll")
    public String deleteAllMarks() {
        String url = "http://localhost:8080/dekanat/marks/deleteAll";

        processPostRequest(url, user.getToken(), null, HttpMethod.DELETE, null);
        return "redirect:/marks";
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }
}
