package server;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import server.game.*;
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
import static server.game.Move.pcmove;
import static server.game.Move.playermove;

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
        System.out.println("hit home controller");
        return "homepage";
    }

    @GetMapping("/play")
    public ModelAndView game(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        System.out.println(session.getId() + " " + login);
        if (session.getAttribute("loggedin") == null) {
            model.addAttribute("login", "user");
        }
        if (login != null) {
            model.addAttribute("login", login);
        }
        System.out.println("hit play controller");
        System.out.println(session.getId() + " " + login);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("play");
        mv.addObject("gameArray", gameArray);

        return mv;
    }

    @PostMapping("/play")
    @ResponseBody
    public int[][] newmove(HttpServletRequest request,
                                @RequestParam int column) {
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");

        int row = playermove(gameArray, column);
        boolean isPlayerWin = CheckWin.look(GameEngine.gameArray, row, column);
        if (isPlayerWin) {
            userWins(user);
            System.out.println("Game Over, players wins");
        }

        computermove = !computermove;

        row = pcmove(gameArray);
        boolean isPCWin = CheckWin.look(GameEngine.gameArray, row, column);
        if (isPCWin) {
            userLoss(user);
            System.out.println("Game Over, PC win");
        }

        computermove = !computermove;

//        recordWin(user, isPlayerWin, isPCWin);

        return gameArray;
    }

    private void userWins(UserModel user) {
        user.wins++;
    }

    private void userLoss(UserModel user) {
        user.losses++;
    }

    private void recordWin(UserModel user, boolean isPlayerwin, boolean isPCWin) {
        if (isPlayerwin && !isPCWin) {
            user.wins++;
        }
        if (isPCWin && !isPlayerwin){
            user.losses++;
        }
        if (!isPCWin && !isPlayerwin) {
            user.ties++;
        }

        userDatabaseRepository.save(user);
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
        System.out.println("hit home controller");
        Date date = new Date();
        model.addAttribute("currenttime", date.toString());

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
        System.out.println("hit home controller");
        Date date = new Date();
        model.addAttribute("currenttime", date.toString());

        List<UserModel> users = userDatabaseRepository.findAll();
        Collections.sort(users);
        model.addAttribute("users", users);
        return "logout";
    }

    @GetMapping("/aboutus")
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