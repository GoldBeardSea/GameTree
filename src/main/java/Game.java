public class Game {

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

    public static int MakeMove (int column, int[][] gameArray) {

        int i =0;
        while (gameArray[i][column] !=0 && i<5){
            i++;
        }
        if (i >5 ){
            // if i > 5, we're off the board, and no move was made.
            return i;
        }
        int piece;
        if (GameEngine.computermove) {
            piece = 1;
        } else{
            piece =-1;
        }

        GameEngine.computermove = !GameEngine.computermove;
        gameArray[i][column]= piece;

        return i;
    }
}
