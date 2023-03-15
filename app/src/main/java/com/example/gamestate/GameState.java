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
                board[i][j] = 'e';
            }
        }
        board[3][3] = 'b';
        board[4][3] = 'w';
        board[3][4] = 'w';
        board[4][4] = 'b';
    }

    @Override
    public String toString(){
        return "Number of black pieces: " + numBlackPieces + "/nNumber of white pieces: " + numWhitePieces + "/nIs black's turn: " + isBlackTurn
                + "/nIs white's turn: " + !isBlackTurn + "/nIs game over: " + gameOver;
    }

    public boolean isValidMove(){

    }
    public boolean makeMove(Boolean color){
        if(isValidMove()){

            // Perform the move

            isBlackTurn = !isBlackTurn;
            return true;
        }
        else
            return false;
    }

}
