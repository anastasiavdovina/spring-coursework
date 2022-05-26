package main.controller;

import main.entity.Groups;
import main.entity.People;
import main.entity.Subjects;
import main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import static main.utility.Utils.*;

@Controller
public class PeopleController {
    private User user;

    @GetMapping( "/people/add")
    public String addPerson(Model model) {
        model.addAttribute("title", "Add people");

        String groupsURL = "http://localhost:8080/dekanat/groups/all";
        List<Groups> groupsList = processGetRequest(groupsURL, List.class);
        model.addAttribute("groups", groupsList);
        List<String> roles = new ArrayList<>();
        roles.add("teacher");
        roles.add("student");
        model.addAttribute("roles", roles);
        return "people/addPerson";
    }

    @PostMapping("/people/add")
    public String addPeople(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String fatherName,
                            @RequestParam long groupId, @RequestParam String type) {
        String groupURL = "http://localhost:8080/dekanat/groups/" + groupId;
        Groups group = processGetRequest(groupURL, Groups.class);
        String url = "http://localhost:8080/dekanat/people/add";
        String json = "{\n" +
                "\"firstName\":" + "\"" + firstName + "\",\n" +
                "\"lastName\":" + "\"" + lastName + "\",\n" +
                "\"fatherName\":" + "\"" + fatherName + "\",\n" +
                "\"type\":" + "\"" + type + "\",\n" +
                "\"groupId\":" + "{" + "\n\"id\":" + group.getId() + ",\n" + "\"name\":" + "\"" + group.getName() + "\"\n}" + "\n}";

        processPostRequest(url, user.getToken(), json, HttpMethod.POST, MediaType.APPLICATION_JSON);
        return "redirect:/people";
    }

    @GetMapping("/people")
    public String getAllPeople(Model model) {
        model.addAttribute("title", "People");

        String url = "http://localhost:8080/dekanat/people/all";
        List<People> people = processGetRequest(url, List.class);
        model.addAttribute("people", people);

        return "people/allPeople";
    }

    @GetMapping("/people/findByGroupAndType")
    public String getAllPeopleByGroupAndType(Model model) {
        model.addAttribute("title", "Find by group and type");
        String groupsURL = "http://localhost:8080/dekanat/groups/all";
        List<Groups> groupsList = processGetRequest(groupsURL, List.class);
        model.addAttribute("groups", groupsList);

        List<String> types = new ArrayList<String>();
        types.add("teacher");
        types.add("student");
        model.addAttribute("types", types);

        return "people/groupAndType";
    }

    @GetMapping("/people/findByGroupAndTypeRes")
    public String getAllPeopleByGroupAndType(@RequestParam String type, @RequestParam long group,Model model) {
        model.addAttribute("title", "Find by group and type");

        String groupURL = "http://localhost:8080/dekanat/groups/" + group;
        Groups groupTemp = processGetRequest(groupURL, Groups.class);
        model.addAttribute("group", groupTemp);
        model.addAttribute("type", type);

        String url = "http://localhost:8080/dekanat/people/findByGroupAndType/" + group + "/" + type;
        List<People> peopleList = processGetRequest(url, List.class);
        model.addAttribute("people", peopleList);

        return "people/groupAndTypeResult";
    }

    @GetMapping("/people/findBySubjectAndType")
    public String getAllPeopleBySubjectAndType(Model model) {
        model.addAttribute("title", "Find by subject and type");
        String subjectsURL = "http://localhost:8080/dekanat/subjects/all";
        List<Subjects> subjectsList = processGetRequest(subjectsURL, List.class);
        model.addAttribute("subjects", subjectsList);

        List<String> types = new ArrayList<String>();
        types.add("teacher");
        types.add("student");
        model.addAttribute("types", types);

        return "people/subjectAndType";
    }

    @GetMapping("/people/findBySubjectAndTypeRes")
    public String getAllPeopleBySubjectAndType(@RequestParam String type, @RequestParam long subject,Model model) {
        model.addAttribute("title", "Find by subject and type");

        String subjectURL = "http://localhost:8080/dekanat/subjects/" + subject;
        Subjects subjectTemp = processGetRequest(subjectURL, Subjects.class);
        model.addAttribute("subject", subjectTemp);
        model.addAttribute("type", type);

        String url = "http://localhost:8080/dekanat/people/findByGroupAndType/" + subject + "/" + type;
        List<People> peopleList = processGetRequest(url, List.class);
        model.addAttribute("people", peopleList);

        return "people/subjectAndTypeRes";
    }

    @PostMapping("/people/delete/{id}")
    public String deletePersonById(@PathVariable("id") long id) {
        String url = "http://localhost:8080/dekanat/people/delete/" + id;

        processPostRequest(url, user.getToken(), null, HttpMethod.DELETE, null);
        return "redirect:/people";
    }

    @PostMapping("/people/deleteAll")
    public String deleteAllPeople() {
        String url = "http://localhost:8080/dekanat/people/deleteAll";

        processPostRequest(url, user.getToken(), null, HttpMethod.DELETE, null);
        return "redirect:/people";
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }
}
