// @authors - Hung-Nghi Vu, Malory Morey, Masato Tsuji
// @version - 3/17/2023
package com.example.gamestate;

public class GameState {

    public int numBlackPieces = 2;
    public int numWhitePieces = 2;

    //Finding coordinates of mouse
    public double touchX;
    public double touchY;
    public boolean isBlackTurn = true;
    public boolean gameOver = false;

    char[][] board = new char[8][8];
    // black piece = 'b'
    //white piece = 'w'
    //empty = 'w'


    public GameState(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = 'e'; // 'e' for empty
            }
        }
        board[3][3] = 'b'; // 'b' for black piece
        board[4][3] = 'w'; // 'w' for white piece
        board[3][4] = 'w';
        board[4][4] = 'b';
    }
    public GameState(GameState gs){
        numBlackPieces = gs.getNumBlackPieces();
        numWhitePieces = gs.getNumWhitePieces();
        isBlackTurn = gs.getIsBlackTurn();
        gameOver = gs.getGameOver();
        board = gs.getBoard();
    }
    public int getNumBlackPieces(){
        return this.numBlackPieces;
    }
    public int getNumWhitePieces(){
        return this.numWhitePieces;
    }
    public boolean getIsBlackTurn(){
        return this.isBlackTurn;
    }
    public boolean getGameOver(){
        return this.gameOver;
    }
    public char[][] getBoard(){
        return this.board;
    }

    public void setIsBlackTurn(boolean b){this.isBlackTurn = b;}



    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < 8; i++){
            for(int j= 0; j <8; j++){
                if(board[i][j] == 'b'){
                    s += "/nBlack piece at [" + i +"][" + j + "]";
                }
                else if (board[i][j] == 'w') {
                    s += "/nWhite piece at [" + i +"][" + j + "]";
                }
                else {
                    s += "/nEmpty at [" + i + "][" + j + "]";
                }
            }
        }
        return "Number of black pieces: " + numBlackPieces + "/nNumber of white pieces: " + numWhitePieces + "/nIs black's turn: " + isBlackTurn
                + "/nIs white's turn: " + !isBlackTurn + "/nIs game over: " + gameOver + s;
    }

    public boolean isValidMove(int row, int col) {
        if (!(board[row][col] == 'e')) {
            return false;
        } else {
            boolean oneOrMore = false;
            boolean hasBlackPieceEnd = false;
            boolean hasWhitePieceEnd = false;
            boolean diffColorAdj = false;
            int i;
            int j;
            if (isBlackTurn) {
                //Check above the piece
                if (row - 1 != -1) {
                    if (board[row - 1][col] == 'w') {
                        diffColorAdj = true;
                    }
                }
                for (i = row - 1; i > -1; i--) {
                    if (board[i][col] == 'w') {
                        oneOrMore = true;
                    }
                    if (board[i][col] == 'b') {
                        hasBlackPieceEnd = true;
                    }
                }
                if (oneOrMore && hasBlackPieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasBlackPieceEnd = false;
                diffColorAdj = false;
                //Check below the piece
                if (row + 1 != 8) {
                    if (board[row + 1][col] == 'w') {
                        diffColorAdj = true;
                    }
                }
                for (i = row + 1; i < 8; i++) {
                    if (board[i][col] == 'w') {
                        oneOrMore = true;
                    }
                    if (board[i][col] == 'b') {
                        hasBlackPieceEnd = true;
                    }
                }
                if (oneOrMore && hasBlackPieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasBlackPieceEnd = false;
                diffColorAdj = false;
                //Check to the left of the piece
                if (col - 1 != -1) {
                    if (board[row][col - 1] == 'w') {
                        diffColorAdj = true;
                    }
                }
                for (i = col - 1; i > -1; i--) {
                    if (board[row][i] == 'w') {
                        oneOrMore = true;
                    }
                    if (board[row][i] == 'b') {
                        hasBlackPieceEnd = true;
                    }
                }
                if (oneOrMore && hasBlackPieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasBlackPieceEnd = false;
                diffColorAdj = false;
                //Check to the right of the piece
                if (col + 1 != 8) {
                    if (board[row][col + 1] == 'w') {
                        diffColorAdj = true;
                    }
                }
                for (i = col + 1; i < 8; i++) {
                    if (board[row][i] == 'w') {
                        oneOrMore = true;
                    }
                    if (board[row][i] == 'b') {
                        hasBlackPieceEnd = true;
                    }
                }
                if (oneOrMore && hasBlackPieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasBlackPieceEnd = false;
                diffColorAdj = false;
                //Check diagonally top left of the piece
                if (col - 1 != -1 && row - 1 != -1) {
                    if (board[row - 1][col - 1] == 'w') {
                        diffColorAdj = true;
                    }
                }
                i = row - 1;
                j = col - 1;
                while (i > -1 && j > -1) {
                    if (board[i][j] == 'w') {
                        oneOrMore = true;
                    }
                    if (board[i][j] == 'b') {
                        hasBlackPieceEnd = true;
                    }
                    i--;
                    j--;
                }
                if (oneOrMore && hasBlackPieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasBlackPieceEnd = false;
                diffColorAdj = false;
                //Check diagonally bottom right of the piece
                if (col + 1 != 8 && row + 1 != 8) {
                    if (board[row + 1][col + 1] == 'w') {
                        diffColorAdj = true;
                    }
                }
                i = row + 1;
                j = col + 1;
                while (i < 8 && j < 8) {
                    if (board[i][j] == 'w') {
                        oneOrMore = true;
                    }
                    if (board[i][j] == 'b') {
                        hasBlackPieceEnd = true;
                    }
                    i++;
                    j++;
                }
                if (oneOrMore && hasBlackPieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasBlackPieceEnd = false;
                diffColorAdj = false;
                //Check diagonally bottom left of the piece
                if (col - 1 != -1 && row + 1 != 8) {
                    if (board[row + 1][col - 1] == 'w') {
                        diffColorAdj = true;
                    }
                }
                i = row + 1;
                j = col - 1;
                while (i < 8 && j > -1) {
                    if (board[i][j] == 'w') {
                        oneOrMore = true;
                    }
                    if (board[i][j] == 'b') {
                        hasBlackPieceEnd = true;
                    }
                    i++;
                    j--;
                }
                if (oneOrMore && hasBlackPieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasBlackPieceEnd = false;
                diffColorAdj = false;
                //Check diagonally top right of the piece
                if (col + 1 != 8 && row - 1 != -1) {
                    if (board[col + 1][row - 1] == 'w') {
                        diffColorAdj = true;
                    }
                }
                i = row - 1;
                j = col + 1;
                while (i > -1 && j < 8) {
                    if (board[i][j] == 'w') {
                        oneOrMore = true;
                    }
                    if (board[i][j] == 'b') {
                        hasBlackPieceEnd = true;
                    }
                    i--;
                    j++;
                }
                if (oneOrMore && hasBlackPieceEnd && diffColorAdj) {
                    return true;
                }
            } else { //If it is whites turn
//Check above the piece
                if (row - 1 != -1) {
                    if (board[row - 1][col] == 'b') {
                        diffColorAdj = true;
                    }
                }
                for (i = row - 1; i > -1; i--) {
                    if (board[i][col] == 'b') {
                        oneOrMore = true;
                    }
                    if (board[i][col] == 'w') {
                        hasWhitePieceEnd = true;
                    }
                }
                if (oneOrMore && hasWhitePieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasWhitePieceEnd = false;
                diffColorAdj = false;
                //Check below the piece
                if (row + 1 != 8) {
                    if (board[row + 1][col] == 'b') {
                        diffColorAdj = true;                    }
                }
                for (i = row + 1; i < 8; i++) {
                    if (board[i][col] == 'b') {
                        oneOrMore = true;
                    }
                    if (board[i][col] == 'w') {
                        hasWhitePieceEnd = true;
                    }
                }
                if (oneOrMore && hasWhitePieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasWhitePieceEnd = false;
                diffColorAdj = false;
                //Check to the left of the piece
                if (col - 1 != -1) {
                    if (board[row][col - 1] == 'b') {
                        diffColorAdj = true;                    }
                }
                for (i = col - 1; i > -1; i--) {
                    if (board[row][i] == 'b') {
                        oneOrMore = true;
                    }
                    if (board[row][i] == 'w') {
                        hasWhitePieceEnd = true;
                    }
                }
                if (oneOrMore && hasWhitePieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasWhitePieceEnd = false;
                diffColorAdj = false;
                //Check to the right of the piece
                if (col + 1 != 8) {
                    if (board[row][col + 1] == 'b') {
                        diffColorAdj = true;                    }
                }
                for (i = col + 1; i < 8; i++) {
                    if (board[row][i] == 'b') {
                        oneOrMore = true;
                    }
                    if (board[row][i] == 'w') {
                        hasWhitePieceEnd = true;
                    }
                }
                if (oneOrMore && hasWhitePieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasWhitePieceEnd = false;
                diffColorAdj = false;
                //Check diagonally top left of the piece
                if (col - 1 != -1 && row - 1 != -1) {
                    if (board[row - 1][col - 1] == 'b') {
                        diffColorAdj = true;                    }
                }
                i = row - 1;
                j = col - 1;
                while (i > -1 && j > -1) {
                    if (board[i][j] == 'b') {
                        oneOrMore = true;
                    }
                    if (board[i][j] == 'w') {
                        hasWhitePieceEnd = true;
                    }
                    i--;
                    j--;
                }
                if (oneOrMore && hasWhitePieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasWhitePieceEnd = false;
                //Check diagonally bottom right of the piece
                if (col + 1 != 8 && row + 1 != 8) {
                    if (board[row + 1][col + 1]== 'b') {
                        diffColorAdj = true;                    }
                }
                i = row + 1;
                j = col + 1;
                while (i < 8 && j < 8) {
                    if (board[i][j] == 'b') {
                        oneOrMore = true;
                    }
                    if (board[i][j] == 'w') {
                        hasWhitePieceEnd = true;
                    }
                    i++;
                    j++;
                }
                if (oneOrMore && hasWhitePieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasWhitePieceEnd = false;
                diffColorAdj = false;
                //Check diagonally bottom left of the piece
                if (col - 1 != -1 && row + 1 != 8) {
                    if (board[row + 1][col - 1] == 'b') {
                        diffColorAdj = true;                    }
                }
                i = row + 1;
                j = col - 1;
                while (i < 8 && j > -1) {
                    if (board[i][j] == 'b') {
                        oneOrMore = true;
                    }
                    if (board[i][j] == 'w') {
                        hasWhitePieceEnd = true;
                    }
                    i++;
                    j--;
                }
                if (oneOrMore && hasWhitePieceEnd && diffColorAdj) {
                    return true;
                }
                oneOrMore = false;
                hasWhitePieceEnd = false;
                diffColorAdj = false;
                //Check diagonally top right of the piece
                if (col + 1 != 8 && row - 1 != -1) {
                    if (board[row - 1][col + 1] == 'b') {
                        diffColorAdj = true;                    }
                }
                i = row - 1;
                j = col + 1;
                while (i > -1 && j < 8) {
                    if (board[i][j] == 'b') {
                        oneOrMore = true;
                    }
                    if (board[i][j] == 'w') {
                        hasWhitePieceEnd = true;
                    }
                    i--;
                    j++;
                }
                if (oneOrMore && hasWhitePieceEnd && diffColorAdj) {
                    return true;
                }

            }
            return false;
        }
    }
    public boolean makeMove(int row, int col){
        if(isValidMove(row, col)){
            board = new char[8][8];
            isBlackTurn = !isBlackTurn;
            return true;
        }
        else
            return false;
    }

    /*
     * Dumb AI moves
     * 100x100
     * 700x200
     * 1700x1200
     */
    public void dumbAIMove() {
        boolean haveMoved = false;
        if (!isBlackTurn) {//if its the computers turn
            // Iterate through the board until you find an empty spot that is a valid move,
            // Then place piece
           for (int i = 0; i<8; i++) {
               for (int j = 0; j < 8; j++) {
                   if (haveMoved == false && isValidMove(i, j)) {
                       if (board[i][j] == 'e') {
                           board[i][j] = 'w';//puts white piece
                           haveMoved =true;
                       }
                       else{
                           haveMoved = false;
                       }
                   }
                   else if (haveMoved == true) {

                   }
               }
           }

        }
        else {
            //if its not the computers turn, don't do anything
        }

    }


    /*
     * checks weather the game is over or not
     */
    private boolean gameOver() {
        boolean full = false;
        int countTotal = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (!isBlackTurn || isBlackTurn )
                {
                    countTotal++;
                }
            }
        }
        if (countTotal == 64)
        {
            full = true;
        }
        return full;
    }

    /*
     shows the end game message and says who won with the amount of disks they had.
     */
    public void endGame() {
        int blackC = 0;
        int whiteC = 0;
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'b') {
                    blackC++;
                }
                if (board[i][j] == 'w'){
                    whiteC++;
                }
            }
        }
        if (blackC > whiteC){
            //show message balck wins
        }
        else if (whiteC > blackC) {
            //message saying white wins
        }
        else {
            //show tie message
        }


    }
    public boolean dumbMakeMove(char c){
        int x =0;
        int y =0;
        if(touchX > 400 && touchX < 500){
            x = 0;
        } else if (touchX < 600) {
            x = 1;
        }else if (touchX < 700) {
            x = 2;
        }else if (touchX < 800) {
            x = 3;
        }else if (touchX < 900) {
            x = 4;
        }else if (touchX < 1000) {
            x = 5;
        }else if (touchX < 1100) {
            x = 6;
        } else if (touchX < 1200) {
            x = 7;
        }
        else
            return false;
        if(touchY > 100 && touchY < 200){
            y = 0;
        } else if (touchY < 300) {
            y = 1;
        }else if (touchY < 400) {
            y = 2;
        }else if (touchY < 500) {
            y = 3;
        }else if (touchY < 600) {
            y = 4;
        }else if (touchY < 700) {
            y = 5;
        }else if (touchY < 800) {
            y = 6;
        } else if (touchY < 900) {
            y = 7;
        }
        else
            return false;
        if(isValidMove(y, x)){
            board[y][x] = c;
            return true;
        }
        return false;
    }
}
