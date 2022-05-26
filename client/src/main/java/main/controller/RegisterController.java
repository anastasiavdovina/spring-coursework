package main.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

import static main.utility.Utils.*;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("title", "Register");
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        String url = "http://localhost:8080/dekanat/auth/register";
        String json = "{\n" +
                "\"username\":" + "\"" + username + "\",\n" +
                "\"password\":" + "\"" + password + "\",\n" +
                "\"roles\":" + "[\"ROLE_" + role.toUpperCase(Locale.ROOT) + "\"]\n" +
                "}";

        processPostRequest(url, null, json, HttpMethod.POST, MediaType.APPLICATION_JSON);
        return "redirect:/login";
    }
}
