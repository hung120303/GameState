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
        }
        else {
            boolean isEmpty = false;
            int i;
            int j;
            if (isBlackTurn) {
                //Check above the piece
                if (row - 1 != -1) {
                    //Check if space in direction is opposite color
                    if (board[row - 1][col] == 'w') {
                        while(!isEmpty) { //Until an empty space or end of board is reached, go through the direction on board
                            for (i = row - 1; i > -1; i--) {
                                if(board[i][col] == 'b' && !isEmpty){ //If at some point, a space in the direction has a black piece, there is a valid move for the direction
                                    return true;
                                }
                                else if(board[i][col] == 'e' || i == 0){ //Stop at empty space or end of board
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check below the piece
                if (row + 1 != 8) {
                    if (board[row + 1][col] == 'w') {
                        while(!isEmpty) {
                            for (i = row + 1; i < 8; i++) {
                                if (board[i][col] == 'b'&& !isEmpty) {
                                    return true;
                                }
                                else if (board[i][col] == 'e' || i == 7) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the left of the piece
                if (col - 1 != -1) {
                    if (board[row][col - 1] == 'w') {
                        while(!isEmpty) {
                            for (i = col - 1; i > -1; i--) {
                                if (board[row][i] == 'b'&& !isEmpty) {
                                    return true;
                                }
                                else if (board[row][i] == 'e' || i ==0 ) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the right of the piece
                if (col + 1 != 8) {
                    if (board[row][col + 1] == 'w') {
                       while(!isEmpty){
                           for (i = col + 1; i < 8; i++) {
                               if (board[row][i] == 'b'&& !isEmpty) {
                                   return true;
                               }
                               else if(board[row][i] == 'e' || i == 7){
                                   isEmpty = true;
                               }
                           }
                       }
                    }
                }
                isEmpty = false;
                //Check diagonally top left of the piece
                if (col - 1 != -1 && row - 1 != -1) {
                    if (board[row - 1][col - 1] == 'w') {
                        i = row - 1;
                        j = col - 1;
                        while(!isEmpty){
                            while (i > -1 && j > -1) {
                                if (board[i][j] == 'b' && !isEmpty) {
                                    return true;
                                }
                                else if(board[i][j] == 'e' || i == 0 || j ==0){
                                    isEmpty = true;
                                }
                                i--;
                                j--;
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally bottom right of the piece
                if (col + 1 != 8 && row + 1 != 8) {
                    if (board[row + 1][col + 1] == 'w') {
                        i = row + 1;
                        j = col + 1;
                        while (i < 8 && j < 8 && !isEmpty) {
                                if (board[i][j] == 'b' && !isEmpty) {
                                    return true;
                                }
                                else if(board[i][j] == 'e' || i == 7 || j ==7){
                                    isEmpty = true;
                                }
                                i++;
                                j++;
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally bottom left of the piece
                if (col - 1 != -1 && row + 1 != 8) {
                    if (board[row + 1][col - 1] == 'w') {
                        i = row + 1;
                        j = col - 1;
                        while (i < 8 && j > -1 && !isEmpty) {
                                if (board[i][j] == 'b' && !isEmpty) {
                                    return true;
                                }
                                else if(board[i][j] == 'e' || i == 7 || j == 0){
                                    isEmpty = true;
                                }
                                i++;
                                j--;
                        }

                    }
                }
                isEmpty = false;
                //Check diagonally top right of the piece
                if (col + 1 != 8 && row - 1 != -1) {
                    if (board[row - 1][col + 1] == 'w') {
                        i = row - 1;
                        j = col + 1;
                            while (i > -1 && j < 8 && !isEmpty) {
                                if (board[i][j] == 'b' && !isEmpty) {
                                    return true;
                                }
                                else if(board[i][j] == 'e' || i == 0 || j == 7){
                                    isEmpty = true;
                                }
                                i--;
                                j++;
                            }

                    }
                }
            }
            else {
                //If it is whites turn
                //Check above the piece
                if (row - 1 != -1) {
                    //Check if space in direction is opposite color
                    if (board[row - 1][col] == 'b') {
                        while(!isEmpty) { //Until an empty space is reached, go through the direction on board
                            for (i = row - 1; i > -1; i--) {
                                if(board[i][col] == 'w'&& !isEmpty){ //If at some point, a space in the direction has a black piece, there is a valid move for the direction
                                    return true;
                                }
                                else if(board[i][col] == 'e' || i == 0){ //Stop at empty space
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check below the piece
                if (row + 1 != 8) {
                    if (board[row + 1][col] == 'b') {
                        while(!isEmpty) {
                            for (i = row + 1; i < 8; i++) {
                                if (board[i][col] == 'w'&& !isEmpty) {
                                    return true;
                                }
                                else if (board[i][col] == 'e' || i == 7) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the left of the piece
                if (col - 1 != -1) {
                    if (board[row][col - 1] == 'b') {
                        while(!isEmpty) {
                            for (i = col - 1; i > -1; i--) {
                                if (board[row][i] == 'w'&& !isEmpty) {
                                    return true;
                                }
                                else if (board[row][i] == 'e' || i == 0) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the right of the piece
                if (col + 1 != 8) {
                    if (board[row][col + 1] == 'b') {
                        while(!isEmpty){
                            for (i = col + 1; i < 8; i++) {
                                if (board[row][i] == 'w'&& !isEmpty) {
                                    return true;
                                }
                                else if(board[row][i] == 'e' || i == 7){
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally top left of the piece
                if (col - 1 != -1 && row - 1 != -1) {
                    if (board[row - 1][col - 1] == 'b') {
                        i = row - 1;
                        j = col - 1;
                            while (i > -1 && j > -1 && !isEmpty) {
                                if (board[i][j] == 'w' && !isEmpty) {
                                    return true;
                                }
                                else if(board[i][j] == 'e' || i== 8 || j ==0){
                                    isEmpty = true;
                                }
                                i--;
                                j--;
                            }

                    }
                }
                isEmpty = false;
                //Check diagonally bottom right of the piece
                if (col + 1 != 8 && row + 1 != 8) {
                    if (board[row + 1][col + 1] == 'b') {
                        i = row + 1;
                        j = col + 1;

                            while (i < 8 && j < 8 && !isEmpty) {
                                if (board[i][j] == 'w' && !isEmpty) {
                                    return true;
                                }
                                else if(board[i][j] == 'e' || i== 7 || j ==7){
                                    isEmpty = true;
                                }
                                i++;
                                j++;
                            }

                    }
                }
                isEmpty = false;
                //Check diagonally bottom left of the piece
                if (col - 1 != -1 && row + 1 != 8) {
                    if (board[row + 1][col - 1] == 'b') {
                        i = row + 1;
                        j = col - 1;

                            while (i < 8 && j > -1 && !isEmpty) {
                                if (board[i][j] == 'w' && !isEmpty) {
                                    return true;
                                }
                                else if(board[i][j] == 'e' || i==7 || j ==0){
                                    isEmpty = true;
                                }
                                i++;
                                j--;
                            }

                    }
                }
                isEmpty = false;
                //Check diagonally top right of the piece
                if (col + 1 != 8 && row - 1 != -1) {
                    if (board[row - 1][col + 1] == 'b') {
                        i = row - 1;
                        j = col + 1;

                            while (i > -1 && j < 8 && !isEmpty) {
                                if (board[i][j] == 'w' && !isEmpty) {
                                    return true;
                                }
                                else if(board[i][j] == 'e' || i ==0 || j==7){
                                    isEmpty = true;
                                }
                                i--;
                                j++;
                            }

                    }
                }
            }
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
                   if (haveMoved == false && isValidMove(i, j)) {
                           flip(i, j);
                           board[i][j] = 'w';//puts white piece
                            haveMoved = true;
                           break;
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
            flip(y, x);
            board[y][x] = c;
            return true;
        }
        return false;
    }

    /*
    Method to be used after a valid move is made to flip pieces over
     */
    public void flip(int row, int col) {
        if (!(board[row][col] == 'e')) {
            // false;
        }
        else {
            boolean isEmpty = false;
            int i;
            int j;
            if (isBlackTurn) {
                //Check above the piece
                if (row - 1 != -1) {
                    //Check if space in direction is opposite color
                    if (board[row - 1][col] == 'w') {
                        while(!isEmpty) { //Until an empty space or end of board is reached, go through the direction on board
                            for (i = row - 1; i > -1; i--) {
                                if(board[i][col] == 'b'){ //If at some point, a space in the direction has a black piece, there is a valid move for the direction
                                    for(int x = row -1; x > i; x--){
                                        if(board[x][col] == 'w' && !isEmpty){
                                            board[x][col] = 'b';
                                        }
                                    }
                                    isEmpty = true;
                                }
                                else if(board[i][col] == 'e' || i == 0){ //Stop at empty space or end of board
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check below the piece
                if (row + 1 != 8) {
                    if (board[row + 1][col] == 'w') {
                        while(!isEmpty) {
                            for (i = row + 1; i < 8; i++) {
                                if (board[i][col] == 'b') {
                                    for(int x = row +1; x < i; x++){
                                        if(board[x][col] == 'w' && !isEmpty){
                                            board[x][col] = 'b';
                                        }
                                    }
                                    isEmpty = true;
                                }
                                else if (board[i][col] == 'e' || i == 7) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the left of the piece
                if (col - 1 != -1) {
                    if (board[row][col - 1] == 'w') {
                        while(!isEmpty) {
                            for (i = col - 1; i > -1; i--) {
                                if (board[row][i] == 'b') {
                                    for(int x = col -1; x > i; x--){
                                        if(board[row][x] == 'w'&& !isEmpty){
                                            board[row][x] = 'b';
                                        }
                                    }
                                    isEmpty = true;
                                }
                                else if (board[row][i] == 'e' || i ==0 ) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the right of the piece
                if (col + 1 != 8) {
                    if (board[row][col + 1] == 'w') {
                        while(!isEmpty){
                            for (i = col + 1; i < 8; i++) {
                                if (board[row][i] == 'b') {
                                    for(int x = col +1; x < i; x++){
                                        if(board[row][x] == 'w'&& !isEmpty){
                                            board[row][x] = 'b';
                                        }
                                    }
                                    isEmpty = true;
                                }
                                else if(board[row][i] == 'e' || i == 7){
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally top left of the piece
                if (col - 1 != -1 && row - 1 != -1) {
                    if (board[row - 1][col - 1] == 'w') {
                        i = row - 1;
                        j = col - 1;
                        while(!isEmpty){
                            while (i > -1 && j > -1) {
                                if (board[i][j] == 'b') {
                                    // true;
                                    int x = row -1;
                                    int y = col -1;
                                    while(!isEmpty) {
                                        while (x > i && y > j) {
                                            if (board[x][y] == 'w') {
                                                board[x][y] = 'b';
                                            }
                                            x--;
                                            y--;
                                        }
                                        isEmpty = true;
                                    }
                                }
                                else if(board[i][j] == 'e' || i == 0 || j ==0){
                                    isEmpty = true;
                                }
                                i--;
                                j--;
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally bottom right of the piece
                if (col + 1 != 8 && row + 1 != 8) {
                    if (board[row + 1][col + 1] == 'w') {
                        i = row + 1;
                        j = col + 1;
                        while (i < 8 && j < 8 && !isEmpty) {
                            if (board[i][j] == 'b') {
                                // true;
                                int x = row +1;
                                int y = col +1;
                                while(!isEmpty) {
                                    while (x < i && y < j) {
                                        if (board[x][y] == 'w') {
                                            board[x][y] = 'b';
                                        }
                                        x++;
                                        y++;

                                    }
                                    isEmpty = true;
                                }
                            }
                            else if(board[i][j] == 'e' || i == 7 || j ==7){
                                isEmpty = true;
                            }
                            i++;
                            j++;
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally bottom left of the piece
                if (col - 1 != -1 && row + 1 != 8) {
                    if (board[row + 1][col - 1] == 'w') {
                        i = row + 1;
                        j = col - 1;
                        while (i < 8 && j > -1 && !isEmpty) {
                            if (board[i][j] == 'b') {
                                // true;
                                int x = row +1;
                                int y = col -1;
                                while(!isEmpty) {
                                    while (x < i && y > j) {
                                        if (board[x][y] == 'w') {
                                            board[x][y] = 'b';
                                        }
                                        x++;
                                        y--;
                                    }
                                    isEmpty = true;
                                }
                            }
                            else if(board[i][j] == 'e' || i == 7 || j == 0){
                                isEmpty = true;
                            }
                            i++;
                            j--;
                        }

                    }
                }
                isEmpty = false;
                //Check diagonally top right of the piece
                if (col + 1 != 8 && row - 1 != -1) {
                    if (board[row - 1][col + 1] == 'w') {
                        i = row - 1;
                        j = col + 1;
                        while (i > -1 && j < 8 && !isEmpty) {
                            if (board[i][j] == 'b') {
                                // true;
                                int x = row -1;
                                int y = col +1;
                                while(!isEmpty) {
                                    while (x > i && y < j) {
                                        if (board[x][y] == 'w') {
                                            board[x][y] = 'b';
                                        }
                                        x--;
                                        y++;
                                    }
                                    isEmpty = true;
                                }
                            }
                            else if(board[i][j] == 'e' || i == 0 || j == 7){
                                isEmpty = true;
                            }
                            i--;
                            j++;
                        }

                    }
                }
            }
            else {
                //If it is whites turn
                //Check above the piece
                if (row - 1 != -1) {
                    //Check if space in direction is opposite color
                    if (board[row - 1][col] == 'b') {
                        while(!isEmpty) { //Until an empty space is reached, go through the direction on board
                            for (i = row - 1; i > -1; i--) {
                                if(board[i][col] == 'w'){ //If at some point, a space in the direction has a black piece, there is a valid move for the direction
                                    // true;
                                    for(int x = row -1; x > i; x--){
                                        if(board[x][col] == 'b' && !isEmpty){
                                            board[x][col] = 'w';
                                        }
                                    }
                                    isEmpty = true;
                                }
                                else if(board[i][col] == 'e' || i == 0){ //Stop at empty space
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check below the piece
                if (row + 1 != 8) {
                    if (board[row + 1][col] == 'b') {
                        while(!isEmpty) {
                            for (i = row + 1; i < 8; i++) {
                                if (board[i][col] == 'w') {
                                    // true;
                                    for(int x = row +1; x < i; x++){
                                        if(board[x][col] == 'b'&& !isEmpty){
                                            board[x][col] = 'w';
                                        }
                                    }
                                    isEmpty = true;
                                }
                                else if (board[i][col] == 'e' || i == 7) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the left of the piece
                if (col - 1 != -1) {
                    if (board[row][col - 1] == 'b') {
                        while(!isEmpty) {
                            for (i = col - 1; i > -1; i--) {
                                if (board[row][i] == 'w') {
                                    // true;
                                    for(int x = col -1; x > i; x--){
                                        if(board[row][x] == 'b'&& !isEmpty){
                                            board[row][x] = 'w';
                                        }
                                    }
                                    isEmpty = true;
                                }
                                else if (board[row][i] == 'e' || i == 0) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the right of the piece
                if (col + 1 != 8) {
                    if (board[row][col + 1] == 'b') {
                        while(!isEmpty){
                            for (i = col + 1; i < 8; i++) {
                                if (board[row][i] == 'w') {
                                    // true;
                                    for(int x = col +1; x < i; x++){
                                        if(board[row][x] == 'b'&& !isEmpty){
                                            board[row][x] = 'w';
                                        }
                                    }
                                    isEmpty = true;

                                }
                                else if(board[row][i] == 'e' || i == 7){
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally top left of the piece
                if (col - 1 != -1 && row - 1 != -1) {
                    if (board[row - 1][col - 1] == 'b') {
                        i = row - 1;
                        j = col - 1;
                        while (i > -1 && j > -1 && !isEmpty) {
                            if (board[i][j] == 'w') {
                                // true;
                                int x = row -1;
                                int y = col -1;
                                while(!isEmpty) {
                                    while (x > i && y > j) {
                                        if (board[x][y] == 'b') {
                                            board[x][y] = 'w';
                                        }
                                        x--;
                                        y--;
                                    }
                                    isEmpty = true;
                                }
                            }
                            else if(board[i][j] == 'e' || i== 8 || j ==0){
                                isEmpty = true;
                            }
                            i--;
                            j--;
                        }

                    }
                }
                isEmpty = false;
                //Check diagonally bottom right of the piece
                if (col + 1 != 8 && row + 1 != 8) {
                    if (board[row + 1][col + 1] == 'b') {
                        i = row + 1;
                        j = col + 1;

                        while (i < 8 && j < 8 && !isEmpty) {
                            if (board[i][j] == 'w') {
                                // true;
                                int x = row +1;
                                int y = col +1;
                                while(!isEmpty) {
                                    while (x < i && y < j) {
                                        if (board[x][y] == 'b') {
                                            board[x][y] = 'w';
                                        }
                                        x++;
                                        y++;
                                    }
                                    isEmpty = true;
                                }
                            }
                            else if(board[i][j] == 'e' || i== 7 || j ==7){
                                isEmpty = true;
                            }
                            i++;
                            j++;
                        }

                    }
                }
                isEmpty = false;
                //Check diagonally bottom left of the piece
                if (col - 1 != -1 && row + 1 != 8) {
                    if (board[row + 1][col - 1] == 'b') {
                        i = row + 1;
                        j = col - 1;

                        while (i < 8 && j > -1 && !isEmpty) {
                            if (board[i][j] == 'w') {
                                // true;
                                int x = row +1;
                                int y = col -1;
                                while(!isEmpty) {
                                    while (x < i && y > j) {
                                        if (board[x][y] == 'b') {
                                            board[x][y] = 'w';
                                        }
                                        x++;
                                        y--;
                                    }
                                    isEmpty = true;
                                }
                            }
                            else if(board[i][j] == 'e' || i==7 || j ==0){
                                isEmpty = true;
                            }
                            i++;
                            j--;
                        }

                    }
                }
                isEmpty = false;
                //Check diagonally top right of the piece
                if (col + 1 != 8 && row - 1 != -1) {
                    if (board[row - 1][col + 1] == 'b') {
                        i = row - 1;
                        j = col + 1;

                        while (i > -1 && j < 8 && !isEmpty) {
                            if (board[i][j] == 'w') {
                                // true;
                                int x = row -1;
                                int y = col +1;
                                while(!isEmpty) {
                                    while (x > i && y < j) {
                                        if (board[x][y] == 'b') {
                                            board[x][y] = 'w';
                                        }
                                        x--;
                                        y++;
                                    }
                                    isEmpty = true;
                                }
                            }
                            else if(board[i][j] == 'e' || i ==0 || j==7){
                                isEmpty = true;
                            }
                            i--;
                            j++;
                        }

                    }
                }
            }
        }

    }
}
