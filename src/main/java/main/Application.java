package main;

import main.models.UserModel;
import main.repositories.UserDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@SpringBootApplication
public class Application {
    @Autowired
    UserDatabaseRepository userDatabaseRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("http://localhost:8080");

    }

    @GetMapping("/")
    public String homepage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        System.out.println(session.getId() + " " + login);
        if (session.getAttribute("loggedin") == null) {
            model.addAttribute("login", "user");
        }
        if (login != null) {
            model.addAttribute("login", login);
        }
        System.out.println("hit home controller");
        Date date = new Date();
        model.addAttribute("currenttime", date.toString());

        List<UserModel> users = userDatabaseRepository.findAll();
        Collections.sort(users);
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/leaderboard")
    public String leaderboard(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        System.out.println(session.getId() + " " + login);
        if (session.getAttribute("loggedin") == null) {
            model.addAttribute("login", "user");
        }
        if (login != null) {
            model.addAttribute("login", login);
        }
        System.out.println("hit home controller");
        Date date = new Date();
        model.addAttribute("currenttime", date.toString());

        List<UserModel> users = userDatabaseRepository.findAll();
        Collections.sort(users);
        model.addAttribute("users", users);
        return "leaderboard";
    }
}