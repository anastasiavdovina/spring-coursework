package main.controller;

import main.entity.Subjects;
import main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static main.utility.Utils.processGetRequest;
import static main.utility.Utils.processPostRequest;

@Controller
public class SubjectsController {
    private User user;

    @GetMapping("/subjects/add")
    public String addSubject(Model model) {
        model.addAttribute("title", "Add subject");
        return "subjects/addSubject";
    }

    @PostMapping("/subjects/add")
    public String addPeople(@RequestParam String name) {
        String url = "http://localhost:8080/dekanat/subjects/add";
        String json = "{\n" +
                "\"name\":" + "\"" + name + "\"\n" +
                "}";

        processPostRequest(url, user.getToken(), json, HttpMethod.POST, MediaType.APPLICATION_JSON);
        return "redirect:/subjects";
    }

    @GetMapping("/subjects")
    public String getAllSubjects(Model model) {
        model.addAttribute("title", "Subjects");

        String url = "http://localhost:8080/dekanat/subjects/all";
        List<Subjects> subjects = processGetRequest(url, List.class);
        model.addAttribute("subjects", subjects);

        return "subjects/allSubjects";
    }

    @PostMapping("/subjects/delete/{id}")
    public String deleteSubjectById(@PathVariable("id") long id) {
        String url = "http://localhost:8080/dekanat/subjects/delete/" + id;

        processPostRequest(url, user.getToken(), null, HttpMethod.DELETE, null);
        return "redirect:/subjects";
    }

    @PostMapping("/subjects/deleteAll")
    public String deleteAllSubjects() {
        String url = "http://localhost:8080/dekanat/subjects/deleteAll";

        processPostRequest(url, user.getToken(), null, HttpMethod.DELETE, null);
        return "redirect:/subjects";
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

}
