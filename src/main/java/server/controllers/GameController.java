package server.controllers;

import server.game.GameEngine;
import server.game.GameMethods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/play")
public class GameController {
    @GetMapping("/")
    @ResponseBody
    public int[][] game (){
        return server.game.GameEngine.gameArray;
    }

    @PostMapping("/")
    public ModelAndView newmove (HttpServletRequest request,
                                 @RequestParam int column){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("play");
        server.game.GameMethods.MakeMove(column, server.game.GameEngine.gameArray);
        return mv;
    }

}
