package server.game;


import static server.game.GameEngine.computermove;
import static server.game.GameEngine.playing;

public class GameMethods {

    public static int[][] newBoard () {
        int[][] gamearray = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}};

        playing = true;
        computermove = false;
        return gamearray;
    }

    public static int[][] MakeMove (int column, int[][] gameArray) {
        int i=0;
        while (gameArray[i][column] !=0 && i<6){
            i++;
        }
        if (i >5 ){
            // if i > 5, we're off the board, and no move was made.
            return gameArray;
        } else {
            int piece;
            if (GameEngine.computermove) {
                piece = 1;
            } else {
                piece = -1;
            }

            gameArray[i][column] = piece;
            return gameArray;
        }
    }
}
