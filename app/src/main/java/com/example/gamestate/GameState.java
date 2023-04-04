// @authors - Hung-Nghi Vu, Malory Morey, Masato Tsuji
// @version - 3/17/2023
package com.example.gamestate;

public class GameState {

    public int numBlackPieces = 2;
    public int numWhitePieces = 2;

    public boolean isBlackTurn = true;
    public boolean gameOver = false;

    char[][] board = new char[8][8];
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
     * checks weather the game is over or not
     */
    private boolean gameOver() {
        boolean full = false;
        int countTotal = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (isBlackTurn== true || isBlackTurn == false)
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
                if (board[i][j] == 'c') {
                    blackC++;
                }
                if (board[i][j] == 'c'){
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
}
