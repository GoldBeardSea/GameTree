package main.controllers;

import main.game.GameEngine;
import main.game.GameMethods;
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
        return GameEngine.gameArray;
    }

    @PostMapping("/")
    public ModelAndView newmove (HttpServletRequest request,
                                 @RequestParam int column){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("play");
        GameMethods.MakeMove(column, GameEngine.gameArray);
        return mv;
    }

}
