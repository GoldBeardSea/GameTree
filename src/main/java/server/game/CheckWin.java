package server.game;

public class CheckWin {
    static boolean look(int[][] gameArray, int row, int column) {
        int match = gameArray[row][column];

        //Check four in a row going down
        int scoredown = 0;
        int i = 1;
        while (row - i > (-1)) {
            if (gameArray[row - i][column] == match) {
                scoredown++;
                i++;
            } else {
                break;
            }
        }

        //  Check four in a row going left and right
        int scorelr = 0;
        int scoredownright = 0;
        int scoreupright = 0;
        boolean checkleft = true;
        boolean checkright = true;
        boolean checkupleft = true;
        boolean checkdownright = true;
        boolean checkdownleft = true;
        boolean checkupright = true;
        for (i = 1; i < 4; i++) {
            //  Checking due left, careful to stay in bounds
            if ((column - i) > -1 && checkleft == true) {
                if (gameArray[row][column - i] == match) {
                    scorelr++;
                } else {
                    checkleft = false;
                }
            }
            //  Checking due right, careful to stay in bounds
            if ((column + i) < 7 && checkright == true) {
                if (gameArray[row][column + i] == match) {
                    scorelr++;
                } else {
                    checkright = false;
                }
            }

            //  Checking down-right careful to stay in bounds
            if (row - i > -1 && (column + i) < 7 && checkdownright == true) {
                if (gameArray[row - i][column + i] == match) {
                    scoredownright++;
                } else {
                    checkdownright = false;
                }
            }

            //  Checking up-left careful to stay in bounds
            if (row + i < 6 && (column - i) > -1 && checkupleft == true) {
                if (gameArray[row + i][column - i] == match) {
                    scoredownright++;
                } else {
                    checkupleft = false;
                }
            }

            //  Checking up-right careful to stay in bounds
            if (row + i < 6 && (column + i) < 7 && checkupright == true) {
                if (gameArray[row + i][column + i] == match) {
                    scoreupright++;
                } else {
                    checkupright = false;
                }
            }

            //  Checking down-left careful to stay in bounds
            if (row - i > -1 && (column - i) > -1 && checkdownleft == true) {
                if (gameArray[row - i][column - i] == match) {
                    scoreupright++;
                } else {
                    checkdownleft = false;
                }
            }
        }

        if (  scorelr > 2 || scoredown > 2 ||scoreupright > 2 || scoredownright > 2) {
            return true;
        }
        return false;
    }

}
