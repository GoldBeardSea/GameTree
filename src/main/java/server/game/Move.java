package server.game;

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

        pcmove(ar);
    }

    public static int pcmove(int[][] gameArray) {
        int column = BFS.search(gameArray);
        GameMethods.MakeMove(column, GameEngine.gameArray);
        int row = -2;
        for (int i = 5; i > -1; i--) {
            if (GameEngine.gameArray[i][column] != 0) {
                row = i;
                i = -100;
            }
        }
        return row;
    }

    public static int playermove(int[][] gameArray, int column) {
        GameMethods.MakeMove(column, GameEngine.gameArray);
        int row = -2;
        for (int i = 5; i > -1; i--) {
            if (GameEngine.gameArray[i][column] != 0) {
                row = i;
                i = -100;
            }
        }
        return row;
    }

    public static int chooseRandomMove() {
        int column = (int) Math.floor(Math.random() * 7);
        GameMethods.MakeMove(column, GameEngine.gameArray);
        int row = -2;
        for (int i = 5; i > -1; i--) {
            if (GameEngine.gameArray[i][column] != 0) {
                row = i;
                i = -100;
            }
        }
        return row;
    }
}