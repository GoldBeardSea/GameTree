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

@Controller
@RequestMapping("/auth")
@SessionAttributes("username")
public class AuthController {
    @Autowired
    UserDatabaseRepository userDB;


    @PostMapping("/register")
    public ModelAndView register(@RequestParam String username, @RequestParam String password, @RequestParam String bio) {
        UserModel user = new UserModel();
        userDB.save(user);

        ModelAndView mv = new ModelAndView();

//        if (UserModel.getUserByName(username) != null) {
//            mv.setViewName("loginerror");
//            mv.addObject("error", "Sorry, that username already exists. Choose another.");
//        } else {
//            UserDB.createUser(username, password, bio);
//            mv.setViewName("loggedin");
//            mv.addObject("username", username);
//        }
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(
            HttpServletRequest request,
            @RequestParam String username,
            @RequestParam String password
    ) {
        ModelAndView mv = new ModelAndView();

//        User user = UserDB.getUserByName(username);
//        if (user == null) {
//            mv.setViewName("loginerror");
//            mv.addObject("error", "Username not found. Choose another.");
//        } else {
//            boolean isCorrectPassword = user.checkPassword(password);
//            if (isCorrectPassword) {
//                mv.setViewName("loggedin");
//                mv.addObject("username", username);
//
//                HttpSession session = request.getSession();
//                session.setAttribute("loggedin", true);
//            } else {
//                mv.setViewName("loginerror");
//                mv.addObject("error", "Wrong password. Try again.");
//            }
//        }

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
