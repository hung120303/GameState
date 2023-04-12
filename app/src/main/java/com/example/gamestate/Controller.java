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
        gameState.touchX = motionEvent.getX();
        gameState.touchY = motionEvent.getY();
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
                    }
                } else {
                    if (gameState.dumbMakeMove('w')) {
                        Log.d("click", "white moves");
                        gameState.setIsBlackTurn(true);
                    }
                }
            }
            else if(gameState.AIGame) {
                if (gameState.dumbMakeMove('b')) {
                    Log.d("click", "black moves");
                    gameState.setIsBlackTurn(false);

                    //White AI move
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!gameState.isDumb) {
                                gameState.godAIMove();
                            } else {
                                gameState.dumbAIMove();
                            }
                            view.invalidate();
                            Log.d("click", "white moves");
                            gameState.setIsBlackTurn(true);
                        }
                    }, 2000);
                }
            }
        return false;    }

    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }
    }

}
