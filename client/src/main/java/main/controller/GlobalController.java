package main.controller;

import main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {

    private User user;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("title", "Homepage");
        return "temp";
    }

    @GetMapping("/account")
    public String getAccountPage(Model model) {
        model.addAttribute("title", "Account");
        model.addAttribute("login", user.getUsername());
        model.addAttribute("token", user.getToken());
        return "auth/myAccount";
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }
}
