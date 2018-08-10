package server;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import server.models.UserModel;
import server.repositories.UserDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static server.game.GameEngine.computermove;
import static server.game.GameEngine.gameArray;
import static server.game.Move.chooseRandomMove;
import static server.game.Move.playermove;
import static server.game.GameEngine.playing;

@Controller
@SpringBootApplication
public class Application {
    @Autowired
    UserDatabaseRepository userDatabaseRepository;
    @Autowired
    UserDatabaseRepository UserDB;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("http://localhost:8080");

    }

    @PostMapping("/newgame")
    public ModelAndView newgame() {
        System.out.println("hihi");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("play");
        gameArray = server.game.GameMethods.newBoard();
        mv.addObject("gameArray", gameArray);
        return mv;
    }


    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        System.out.println(session.getId() + " " + login);
        if (session.getAttribute("loggedin") == null) {
            model.addAttribute("login", "user");
        }
        if (login != null) {
            model.addAttribute("login", login);
        }
        System.out.println("hit / controller");
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
        System.out.println("hit leader controller");
        Date date = new Date();
        model.addAttribute("currenttime", date.toString());

        List<UserModel> users = userDatabaseRepository.findAll();
        Collections.sort(users);
        model.addAttribute("users", users);
        return "leaderboard";
    }

    @GetMapping("/homepage")
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
        System.out.println("hit homepage controller");
        return "homepage";
    }

    @GetMapping("/play")
    public ModelAndView game() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("play");
        mv.addObject("gameArray", gameArray);
        mv.addObject("playing", playing);

        return mv;
    }

    @PostMapping("/play")
    @ResponseBody
    public int[][] newmove(HttpServletRequest request,
                                @RequestParam int column) {
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        if (playing) {
            playermove(gameArray, column);
            if (!playing) {
                System.out.println("Winning score allocated");
                user.wins++;
                userDatabaseRepository.save(user);


            }
        }

        computermove = !computermove;
//        pcmove(gameArray);
        if (playing) {
            chooseRandomMove();
            System.out.println("playing = " + playing);
            if (!playing) {
                user.losses++;
                userDatabaseRepository.save(user);

            }
        }
        computermove = !computermove;
        return gameArray;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        System.out.println(session.getId() + " " + login);
        if (session.getAttribute("loggedin") == null) {
            model.addAttribute("login", "user");
        }
        if (login != null) {
            model.addAttribute("login", login);
        }
        System.out.println("hit /login controller");
        Date date = new Date();
        model.addAttribute("currenttime", date.toString())
        ;


        List<UserModel> users = userDatabaseRepository.findAll();
        Collections.sort(users);
        model.addAttribute("users", users);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        System.out.println(session.getId() + " " + login);
        if (session.getAttribute("loggedin") == null) {
            model.addAttribute("login", "user");
        }
        if (login != null) {
            model.addAttribute("login", login);
        }
        System.out.println("hit /logout controller");
        Date date = new Date();
        model.addAttribute("currenttime", date.toString());

        List<UserModel> users = userDatabaseRepository.findAll();
        Collections.sort(users);
        model.addAttribute("users", users);
        return "logout";
    }

    @GetMapping("/about")
    public String aboutus(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        System.out.println(session.getId() + " " + login);
        return "aboutus";
    }

    @RequestMapping("/private")
    public class PrivateController {
        @RequestMapping("/profile")
        public ModelAndView handleProfileRequests(HttpServletRequest request) {
            String servlet = request.getServletPath();
            ModelAndView mv = new ModelAndView();

            HttpSession session = request.getSession();
            boolean isLoggedIn = (boolean) session.getAttribute("loggedin");
            System.out.println("/private " + session.getAttribute("loggedin"));
            if (isLoggedIn) {
                mv.setViewName("profile");
            } else {
                mv.setViewName("accessdenied");
            }
            return mv;
        }
    }
}