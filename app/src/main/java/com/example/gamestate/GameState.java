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
    public GameState(int black, int white, boolean blackTurn, boolean gOver, char[][] boardState){
        numBlackPieces = black;
        numWhitePieces = white;
        isBlackTurn = blackTurn;
        gameOver = gOver;
        board = boardState;
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
