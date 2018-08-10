package server.game;

import static server.game.GameEngine.gameArray;
import static server.game.GameEngine.playing;

public class Move {

    public static void main(String[] args) {
        int[][] ar = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}};

        ar = GameMethods.MakeMove(3,ar);

//        pcmove(ar);
    }

//    public static void pcmove(int[][] gameArray) {
//       int column = BFS2.search(gameArray);
//        GameMethods.MakeMove(column, GameEngine.gameArray);
//        int row = -2;
//        for (int i = 5; i > -1; i--) {
//            if (GameEngine.gameArray[i][column] != 0) {
//                row = i;
//                i = -100;
//            }
//        }
//        boolean wins = CheckWin.look(GameEngine.gameArray, row, column);
//        if (wins) {
//            System.out.println("Game Over, PC win");
//            playing=false;
//        }
//
//    }

    public static void playermove(int[][] gameArray, int column) {
        GameMethods.MakeMove(column, GameEngine.gameArray);
        int row = -2;
        for (int i = 5; i > -1; i--) {
            if (GameEngine.gameArray[i][column] != 0) {
                row = i;
                i = -100;
            }
        }
        boolean wins = CheckWin.look(GameEngine.gameArray, row, column);
        if (wins) {
            System.out.println("Game Over, user win");
            playing=false;
        }
    }

    public static void chooseRandomMove() {
        double sum =0;
        int column = -2;
        boolean legalmove=false;
        while (!legalmove) {

            for (int i = 0; i < 3; i++) {
                sum += Math.random();
            }
            column = (int) Math.floor(sum * 7 / 3);
            if (gameArray[5][column]==0) legalmove=true;
        }
        GameMethods.MakeMove(column, GameEngine.gameArray);
        int row = -2;
        for (int i = 5; i > -1; i--) {
            if (GameEngine.gameArray[i][column] != 0) {
                row = i;
                i = -100;
            }
        }
        boolean wins = CheckWin.look(GameEngine.gameArray, row, column);
        if (wins) {
            System.out.println("Game Over, PC win");
            playing=false;
        }
    }
}