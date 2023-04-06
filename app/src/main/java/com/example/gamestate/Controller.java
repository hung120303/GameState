package com.example.gamestate;

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

            gameState.dumbMakeMove('b');
            view.invalidate();
            Log.d("click", "black moves");
            gameState.setIsBlackTurn(false);

            if(!gameState.isBlackTurn){
                gameState.dumbAIMove();
                Log.d("click", "white moves");
                sleep(1000);
                view.invalidate();
                gameState.setIsBlackTurn(true);
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
