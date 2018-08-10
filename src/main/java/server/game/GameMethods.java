package server.game;


public class GameMethods {

    public static int[][] newBoard () {
        int[][] gamearray = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}};
        return gamearray;
    }

    public static int[][] MakeMove (int column, int[][] gameArray) {
        int i=0;
        while (gameArray[i][column] !=0 && i<6){
            i++;
        }
        if (i >5 ){
            // if i > 5, we're off the board, and no Move was made.
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
