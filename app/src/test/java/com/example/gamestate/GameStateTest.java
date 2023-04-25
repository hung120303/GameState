package com.example.gamestate;
import org.junit.Test;

import static org.junit.Assert.*;
public class GameStateTest {
    @Test
    public void endGame() {
        GameState gameState = new GameState();
        gameState.board = new char[][]{
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'}
    };
        gameState.endGame();
        assertTrue(!gameState.blackWinner);
        gameState.board = new char[][]{
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}
        };
        gameState.endGame();
        assertTrue(gameState.blackWinner);
        gameState.board = new char[][]{
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'}
        };
        gameState.endGame();
        assertTrue(gameState.isTie);
    }
    @Test
    public void isValid(){
        GameState gameState = new GameState();
        gameState.board = new char[][]{
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'b'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'b', 'e'},
                {'e', 'e', 'e', 'w', 'e', 'e', 'e', 'e'},
                {'b', 'w', 'e', 'b', 'w', 'b', 'e', 'e'},
                {'e', 'e', 'e', 'w', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'w', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'w', 'e', 'e', 'e', 'e'},
                {'b', 'e', 'e', 'b', 'e', 'e', 'e', 'e'}
        };
        assertTrue(!gameState.isValidMove(0,0));
        assertTrue(gameState.isValidMove(1,3));
        assertTrue(gameState.isValidMove(5,3));
        assertTrue(!gameState.isValidMove(2, 6));

        gameState.board = new char[][]{
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'w'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'w', 'e'},
                {'e', 'e', 'e', 'b', 'e', 'e', 'e', 'e'},
                {'w', 'b', 'e', 'w', 'b', 'w', 'e', 'e'},
                {'e', 'e', 'e', 'b', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'b', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'b', 'e', 'e', 'e', 'e'},
                {'w', 'e', 'e', 'w', 'e', 'e', 'e', 'e'}
        };
        gameState.isBlackTurn= false;
        assertTrue(!gameState.isValidMove(0,0));
        assertTrue(gameState.isValidMove(1,3));
        assertTrue(gameState.isValidMove(5,3));
        assertTrue(!gameState.isValidMove(2, 6));
    }
    
    @Test
    public void flip(){
        GameState gameState = new GameState();
        gameState.board = new char[][]{
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'b', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'w', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}
        };
        gameState.flip(5, 3);
        boolean flippedB = (gameState.board[4][3] == 'b');
        assertTrue(flippedB);

        gameState.board = new char[][]{
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'w', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'b', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
                {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}
        };
        gameState.isBlackTurn = false;
        gameState.flip(5, 3);
        boolean flippedW = (gameState.board[4][3] == 'w');
        assertTrue(flippedW);


    }
    
    

}
