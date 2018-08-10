package server.game;

import server.game.Tree.SeptinaryTree;
import server.game.CheckWin;

import java.util.Collections;
//import org.apache.commons.lang3.SerializationUtils;

public class GameEngine {

    public static void main(String[] args) {
        int[][] arr = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}};

        System.out.println(evaluatePosition(arr));
    }

    public static int[][] gameArray = server.game.GameMethods.newBoard();
    public static boolean computermove = false;
    final static int plydepth = 6;
    public static boolean playing = true;
    public static boolean PCPlaying = true;

    ///////////////////////////////////////////////////////////////


    public static int evaluatePosition (int[][] evalposition) {
        int winningsquares=0;
        int color = -1;
        while (color <2) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    if (evalposition[i][j] == 0) {
                        evalposition[i][j] = color;
                        boolean wins = CheckWin.look(evalposition, i, j);
                        if (wins) {
                            if (color ==1 )winningsquares++;
                            if (color ==-1) winningsquares--;
                        }
                        evalposition[i][j] = 0;
                    }
                }
            }
            color += 2;
        }
        return winningsquares;
    }
}