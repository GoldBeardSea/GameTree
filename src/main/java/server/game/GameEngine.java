package server.game;

import server.game.Tree.SeptinaryTree;
import server.game.CheckWin;
//import org.apache.commons.lang3.SerializationUtils;

public class GameEngine {

    //  Initialize decision tree and game array.
    public static int[][] gameArray = server.game.GameMethods.newBoard();
    public static boolean computermove = false;
    final static int plydepth = 6;

    ///////////////////////////////////////////////////////////////




    private static int evaluatePosition (int[][]evalposition, int color) {
        int winningsquares=0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 6; j++){
                if (evalposition[i][j]==0){
                    evalposition[i][j] = color;
                    boolean wins = CheckWin.look(evalposition,i,j);
                    if (wins) {
                        winningsquares++;
                    }
                    evalposition[i][j]=0;
                }
            }
        }
        return winningsquares;
    }

    private static void chooseRandomMove() {
        int column = (int) Math.floor(Math.random() * 7);
        GameMethods.MakeMove(column, gameArray);
    }

}



//   Game Array [6 rows][7 columns]