package main.controllers;

import main.repositories.UserDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class ApplicationHome {
    @Controller
    @RequestMapping
    public class HomepageController {
        @Autowired
        UserDatabaseRepository userDatabaseRepository;

        @RequestMapping
        public String homepage(Model model) {

            return "index";
        }
    }
}
