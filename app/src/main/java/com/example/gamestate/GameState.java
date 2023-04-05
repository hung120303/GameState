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

    public boolean isValidMove(int row, int col){
        if(!(board[row][col] == 'e')){
            return false;
        }
        else {
            boolean oneOrMore = false;
            boolean hasBlackPieceEnd = false;
            boolean hasWhitePieceEnd = false;
            if(isBlackTurn){
                //Check below the piece
                if(row-1 != -1){
                    if(board[row-1][col] == 'b'){
                        return false;
                    }
                }
                for(int i = row-1; row > -1; i--){
                    if(board[i][col] == 'w'){
                        oneOrMore = true;
                    }
                    if(board[i][col] == 'b'){
                        hasBlackPieceEnd = true;
                    }
                }
                if(oneOrMore && hasBlackPieceEnd){
                    return true;
                }
                //Check above the piece
                if(row+1 != 8){
                    if(board[row+1][col] == 'b'){
                        return false;
                    }
                }
                for(int i = row+1; row < 8; i++){
                    if(board[i][col] == 'w'){
                        oneOrMore = true;
                    }
                    if(board[i][col] == 'b'){
                        hasBlackPieceEnd = true;
                    }
                }
                if(oneOrMore && hasBlackPieceEnd){
                    return true;
                }
                //Check to the left of the piece
                if(col-1 != -1){
                    if(board[row][col-1] == 'b'){
                        return false;
                    }
                }
                for(int i = col-1; col > -1; i--){
                    if(board[row][i] == 'w'){
                        oneOrMore = true;
                    }
                    if(board[row][i] == 'b'){
                        hasBlackPieceEnd = true;
                    }
                }
                if(oneOrMore && hasBlackPieceEnd){
                    return true;
                }
                //Check to the right of the piece
                if(col+1 != 8){
                    if(board[row][col+1] == 'b'){
                        return false;
                    }
                }
                for(int i = col+1; col < 8; i++){
                    if(board[row][i] == 'w'){
                        oneOrMore = true;
                    }
                    if(board[row][i] == 'b'){
                        hasBlackPieceEnd = true;
                    }
                }
                if(oneOrMore && hasBlackPieceEnd){
                    return true;
                }
            }
            else{}

        }
        return false;
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
                   if (haveMoved == false) {
                       if (board[i][j] == 'e' && isValidMove(i, j)) {
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
    public void dumbMakeMove(char c){
        int x =0;
        int y =0;
        if(touchX > 400 && touchX < 500){
            x = 450;
        } else if (touchX < 600) {
            x = 550;
        }else if (touchX < 700) {
            x = 650;
        }else if (touchX < 800) {
            x = 750;
        }else if (touchX < 900) {
            x = 850;
        }else if (touchX < 1000) {
            x = 950;
        }else if (touchX < 1100) {
            x = 1050;
        } else if (touchX < 1200) {
            x = 1150;
        }
        if(touchY > 100 && touchY < 200){
            y = 150;
        } else if (touchY < 300) {
            y = 250;
        }else if (touchY < 400) {
            y = 350;
        }else if (touchY < 500) {
            y = 450;
        }else if (touchY < 600) {
            y = 550;
        }else if (touchY < 700) {
            y = 650;
        }else if (touchY < 800) {
            y = 750;
        } else if (touchY < 900) {
            y = 850;
        }

        if(x == 450 && y == 150){
            board[0][0] = c;
        }
        if(x == 550 && y == 150){
            board[1][0] = c;
        }
        if(x == 650 && y == 150){
            board[2][0] = c;
        }
        if(x == 750 && y == 150){
            board[3][0] = c;
        }
        if(x == 850 && y == 150){
            board[4][0] = c;
        }
        if(x == 950 && y == 150){
            board[5][0] = c;
        }
        if(x == 1050 && y == 150){
            board[6][0] = c;
        }
        if(x == 1150 && y == 150){
            board[7][0] = c;
        }

        if(x == 450 && y == 250){
            board[0][1] = c;
        }
        if(x == 550 && y == 250){
            board[1][1] = c;
        }
        if(x == 650 && y == 250){
            board[2][1] = c;
        }
        if(x == 750 && y == 250){
            board[3][1] = c;
        }
        if(x == 850 && y == 250){
            board[4][1] = c;
        }
        if(x == 950 && y == 250){
            board[5][1] = c;
        }
        if(x == 1050 && y == 250){
            board[6][1] = c;
        }
        if(x == 1150 && y == 250){
            board[7][1] = c;
        }

        if(x == 450 && y == 350){
            board[0][2] = c;
        }
        if(x == 550 && y == 350){
            board[1][2] = c;
        }
        if(x == 650 && y == 350){
            board[2][2] = c;
        }
        if(x == 750 && y == 350){
            board[3][2] = c;
        }
        if(x == 850 && y == 350){
            board[4][2] = c;
        }
        if(x == 950 && y == 350){
            board[5][2] = c;
        }
        if(x == 1050 && y == 350){
            board[6][2] = c;
        }
        if(x == 1150 && y == 350){
            board[7][2] = c;
        }

        if(x == 450 && y == 450){
            board[0][3] = c;
        }
        if(x == 550 && y == 450){
            board[1][3] = c;
        }
        if(x == 650 && y == 450){
            board[2][3] = c;
        }
        if(x == 750 && y == 450){
            board[3][3] = c;
        }
        if(x == 850 && y == 450){
            board[4][3] = c;
        }
        if(x == 950 && y == 450){
            board[5][3] = c;
        }
        if(x == 1050 && y == 450){
            board[6][3] = c;
        }
        if(x == 1150 && y == 450){
            board[7][3] = c;
        }

        if(x == 450 && y == 550){
            board[0][4] = c;
        }
        if(x == 550 && y == 550){
            board[1][4] = c;
        }
        if(x == 650 && y == 550){
            board[2][4] = c;
        }
        if(x == 750 && y == 550){
            board[3][4] = c;
        }
        if(x == 850 && y == 550){
            board[4][4] = c;
        }
        if(x == 950 && y == 550){
            board[5][4] = c;
        }
        if(x == 1050 && y == 550){
            board[6][4] = c;
        }
        if(x == 1150 && y == 550){
            board[7][4] = c;
        }

        if(x == 450 && y == 650){
            board[0][5] = c;
        }
        if(x == 550 && y == 650){
            board[1][5] = c;
        }
        if(x == 650 && y == 650){
            board[2][5] = c;
        }
        if(x == 750 && y == 650){
            board[3][5] = c;
        }
        if(x == 850 && y == 650){
            board[4][5] = c;
        }
        if(x == 950 && y == 650){
            board[5][5] = c;
        }
        if(x == 1050 && y == 650){
            board[6][5] = c;
        }
        if(x == 1150 && y == 650){
            board[7][5] = c;
        }

        if(x == 450 && y == 750){
            board[0][6] = c;
        }
        if(x == 550 && y == 750){
            board[1][6] = c;
        }
        if(x == 650 && y == 750){
            board[2][6] = c;
        }
        if(x == 750 && y == 750){
            board[3][6] = c;
        }
        if(x == 850 && y == 750){
            board[4][6] = c;
        }
        if(x == 950 && y == 750){
            board[5][6] = c;
        }
        if(x == 1050 && y == 750){
            board[6][6] = c;
        }
        if(x == 1150 && y == 750){
            board[7][6] = c;
        }

        if(x == 450 && y == 850){
            board[0][7] = c;
        }
        if(x == 550 && y == 850){
            board[1][7] = c;
        }
        if(x == 650 && y == 850){
            board[2][7] = c;
        }
        if(x == 750 && y == 850){
            board[3][7] = c;
        }
        if(x == 850 && y == 850){
            board[4][7] = c;
        }
        if(x == 950 && y == 850){
            board[5][7] = c;
        }
        if(x == 1050 && y == 850){
            board[6][7] = c;
        }
        if(x == 1150 && y == 850){
            board[7][7] = c;
        }

    }
}
