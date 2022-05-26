package main.controller;

import main.entity.Groups;
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
public class GroupsController {
    private User user;

    @GetMapping( "/groups/add")
    public String addGroup(Model model) {
        model.addAttribute("title", "Add group");
        return "groups/addGroup";
    }

    @PostMapping("/groups/add")
    public String addPeople(@RequestParam String name) {
        String url = "http://localhost:8080/dekanat/groups/add";
        String json = "{\n" +
                "\"name\":" + "\"" + name + "\"\n" +
              "}";

        processPostRequest(url, user.getToken(), json, HttpMethod.POST, MediaType.APPLICATION_JSON);
        return "redirect:/groups";
    }

    @GetMapping("/groups")
    public String getAllGroups(Model model) {
        model.addAttribute("title", "Groups");

        String url = "http://localhost:8080/dekanat/groups/all";
        List<Groups> groups = processGetRequest(url, List.class);
        model.addAttribute("groups", groups);

        return "groups/allGroups";
    }


    @PostMapping("/groups/delete/{id}")
    public String deleteGroupById(@PathVariable("id") long id) {
        String url = "http://localhost:8080/dekanat/groups/delete/" + id;

        processPostRequest(url, user.getToken(), null, HttpMethod.DELETE, null);
        return "redirect:/groups";
    }

    @PostMapping("/groups/deleteAll")
    public String deleteAllGroups() {
        String url = "http://localhost:8080/dekanat/groups/deleteAll";

        processPostRequest(url, user.getToken(), null, HttpMethod.DELETE, null);
        return "redirect:/groups";
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }
}
