package com.example.gamestate;

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
        gameState.dumbAIMove();
    }

    @Override
    public boolean onTouch(android.view.View view, MotionEvent motionEvent) {
        //Get XY Coordinates
        gameState.touchX = motionEvent.getX();
        gameState.touchY = motionEvent.getY();
        if(gameState.isBlackTurn){
            gameState.dumbMakeMove('b');
        }
        return false;
    }
}
