package main.controllers;

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

        @Autowired
        UserStatisticRepository userStatisticRepository;

        @RequestMapping
        public String homepage(Model model) {

            return "index";
        }
    }
}
