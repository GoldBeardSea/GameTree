package main.controllers;

import main.models.UserModel;
import main.repositories.UserDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/auth")
@SessionAttributes("login")
public class AuthController {
    @Autowired
    UserDatabaseRepository userDB;


    @PostMapping("/register")
    public ModelAndView register(@RequestParam String username, String login, @RequestParam String password,
                                 @RequestParam String bio) {
        UserModel user = new UserModel(username, login, password, bio);
        userDB.save(user);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loggedin");
        mv.addObject("login", login);
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(
            HttpServletRequest request,
            @RequestParam String login,
            @RequestParam String password
    ) {
        ModelAndView mv = new ModelAndView();

        UserModel user = null;
        List<UserModel> userList = userDB.findAll();
        for (int i = 0; i < userList.size(); i++) {
            UserModel userIterator = userList.get(i);
            if (userIterator.login.equals(login)) {
                user = userIterator;
                System.out.println("We're here " + user.login);
            }
        }
        if (user == null) {
            mv.setViewName("loginerror");
            mv.addObject("error", "Login not found. Choose another.");
        } else {
            boolean isCorrectPassword = user.checkPassword(password);
            if(isCorrectPassword) {
                mv.setViewName("loggedin");
                mv.addObject("login", login);

                HttpSession session = request.getSession();
                session.setAttribute("loggedin", true);
            } else {
                mv.setViewName("loginerror");
                mv.addObject("error", "Wrong password. Try again.");
            }
        }

        return mv;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("loggedin", false);

        String username = (String) session.getAttribute("username");
        boolean isLoggedIn = (boolean) session.getAttribute("loggedin");
        if (!isLoggedIn) {
            username = "user";
            model.addAttribute("username", "user");
        }

        if (username != null) {
            model.addAttribute("username", username);
        }
        System.out.println(session.getId() + " " + username);
        return new ModelAndView("loggedout");
    }
}
