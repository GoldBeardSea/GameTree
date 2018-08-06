import Tree.SeptinaryTree;
import Tree.TreeNode;
import java.util.Arrays;
import java.util.HashSet;


public class GameEngine{
    public static void main(String[] args) {
        gameArray[3][3]=1;
        gameArray[2][3]=1;
        gameArray[1][3]=1;
        gameArray[0][3]=1;
        gameArray[4][3]=1;
        gameArray[3][2]=1;
        gameArray[3][1]=1;
        gameArray[3][0]=1;

        printBoard(gameArray);
        System.out.println(GameWin(gameArray,2,3));
    }
    //  Initialize decision tree and game array.
    SeptinaryTree gametree = new SeptinaryTree();
    static int[][] gameArray = Game.newBoard();
    static boolean computermove = false;
    final static int plydepth=6;

    ///////////////////////////////////////////////////////////////

    private static void chooseRandomMove(){
        int column = (int)Math.floor(Math.random()*7);
        Game.MakeMove(column, gameArray);
    }

    public static void printBoard(int[][] gameArray){
        for (int i=5; i>-1;i--) {
            System.out.println(Arrays.toString(gameArray[i]));
        }
    }

    private static boolean GameWin (int[][] gameArray, int column, int row) {
        int match = gameArray[row][column];
        //Check four in a row going down
        int score = 0;
        int i = 1;
        while (row - i > (-1)) {
            if (gameArray[row - i][column] == match) {
                score++;
                i++;
            } else {
                break;
            }
        }
        if (score > 2) {
            return true;
        }

        //  Check four in a row going left and right
        score = 0;
        boolean checkleft=true;
        boolean checkright=true;
        for (i=1; i <4; i++){
            //  Checking left, careful to stay in bounds
            if ((row-i) > -1 && checkleft == true){
                if (gameArray[row-i][column] == match){
                    score ++;
                } else {
                    checkleft = false;
                }
            }
            //  Checking right, careful to stay in bounds
            if ((row-i) > -1 && checkleft == true){
                if (gameArray[row+i][column] == match){
                    score ++;
                } else {
                    checkright = false;
                }
            }
        }
        if (score > 2){
            return true;
        }
        return false;
    }


    private static int WinningHole (int[][] gameArray){
    return 2;
    }
}

//   Game Array [6 rows][7 columns]