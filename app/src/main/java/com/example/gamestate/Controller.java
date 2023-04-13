package com.example.gamestate;

import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

public class Controller implements android.view.View.OnClickListener, android.view.View.OnTouchListener {
    private View view;
    private GameState gameState;

    public Controller(View view){
        this.view = view;
        gameState = view.getGameState();
    }
    @Override
    public void onClick(android.view.View view) {

    }

    @Override
    public boolean onTouch(android.view.View view, MotionEvent motionEvent) {
        //Get XY Coordinates
        if(motionEvent != null) {
            gameState.touchX = motionEvent.getX();
            gameState.touchY = motionEvent.getY();
        }
        view.invalidate();
        if(gameState.homeScreen){
            if(!gameState.AIGame) {
                if (gameState.touchX >= 525 && gameState.touchX <= 725 && gameState.touchY >= 600 && gameState.touchY <= 675) {
                    gameState.humanGame = true;
                    gameState.homeScreen = false;
                    view.invalidate();
                } else if (gameState.touchX >= 875 && gameState.touchX <= 1075 && gameState.touchY >= 600 && gameState.touchY <= 675) {
                    gameState.AIGame = true;
                    view.invalidate();
                }
            }
            else{
                if (gameState.touchX >= 525 && gameState.touchX <= 725 && gameState.touchY >= 600 && gameState.touchY <= 675) {
                    gameState.homeScreen = false;
                    gameState.isDumb = true;
                    view.invalidate();
                } else if (gameState.touchX >= 875 && gameState.touchX <= 1075 && gameState.touchY >= 600 && gameState.touchY <= 675) {
                    gameState.homeScreen = false;
                    gameState.isDumb = false;
                    view.invalidate();
                }
            }
        }
        if(gameState.humanGame) {
            if (gameState.isBlackTurn) {
                if (gameState.dumbMakeMove('b')) {
                    Log.d("click", "black moves");
                    gameState.setIsBlackTurn(false);
                    gameState.endGame();

                    //Checks if no move available for WHITE
                    if(!gameState.moveAvailable()){
                        gameState.setIsBlackTurn(true); //Gives turn back to black if no white moves
                    }
                    view.invalidate();
                }
            }
            else {
                if (gameState.dumbMakeMove('w')) {
                    Log.d("click", "white moves");
                    gameState.setIsBlackTurn(true);
                    gameState.endGame();

                    //Checks if no move available for BLACK
                    if(!gameState.moveAvailable()){
                        gameState.setIsBlackTurn(false); //Gives turn back to white if no black moves
                    }
                    view.invalidate();
                }
            }
            //If move isn't valid for both players, end the game
            if(!gameState.moveAvailable()){
                gameState.setIsBlackTurn(!gameState.isBlackTurn);
                if(!gameState.moveAvailable()){
                    gameState.endGame();
                }
                else gameState.setIsBlackTurn(!gameState.isBlackTurn);
            }
        }
        else if(gameState.AIGame) {
            Handler handler = new Handler();
            if ((gameState.dumbMakeMove('b') && gameState.isBlackTurn) || gameState.goAgain) {
                if(gameState.isBlackTurn){Log.d("click", "black moves");}
                gameState.setIsBlackTurn(false);
                gameState.endGame();
                //Checks if no move available for WHITE

                if (!gameState.moveAvailable()) {
                    gameState.setIsBlackTurn(true); //Gives turn back to black if no white moves
                }
                view.invalidate();

                //White AI move
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!gameState.isDumb) {
                            gameState.godAIMove();
                        } else {
                            gameState.dumbAIMove();
                        }
                        gameState.endGame();
                        view.invalidate();
                        if(!gameState.isBlackTurn){ Log.d("click", "white moves");}
                        gameState.setIsBlackTurn(true);
                        if(!gameState.moveAvailable()){
                            gameState.setGoAgain(true);
                        }
                    }
                }, 2000);
            gameState.setGoAgain(false);
            }

        }
        if(gameState.gameOver){
            //Exit and Restart buttons (respectively)
            if (gameState.touchX >= 525 && gameState.touchX <= 725 && gameState.touchY >= 600 && gameState.touchY <= 675) {
                gameState.homeScreen = true;
                gameState.humanGame = false;
                gameState.AIGame = false;
                gameState.isDumb = false;
                gameState.gameOver = false;
                gameState.isBlackTurn = true;
                gameState.clearGameState();
                view.invalidate();
            } else if (gameState.touchX >= 875 && gameState.touchX <= 1075 && gameState.touchY >= 600 && gameState.touchY <= 675) {
                gameState.gameOver = false;
                gameState.isBlackTurn = true;
                gameState.clearGameState();
                view.invalidate();
            }
        }

        return false;
    }

    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }
    }

}
