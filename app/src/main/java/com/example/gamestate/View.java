// @authors - Hung-Nghi Vu, Malory Morey, Masato Tsuji
// @version - 3/17/2023
package com.example.gamestate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class View extends SurfaceView {


    private GameState gameState = new GameState();

    int startX;
    int startY;
    int endX;
    int endY;

    Paint black = new Paint();
    Paint white = new Paint();
    Paint green = new Paint();

    public View(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameState = new GameState();

        black.setColor(0xFF000000);
        white.setColor(0xFFFFFFFF);
        green.setColor(0xFF29AB70);

        setBackgroundColor(Color.WHITE);

    }
    public GameState getGameState(){ return gameState;}

    public void onDraw(Canvas c) {
        super.onDraw(c);
        startX = 400;
        startY = 100;
        endX = 1200;
        endY = 100;


        c.drawRect(400, 100, 1200, 900, green);
        //vertical lines
        for (int i = 0; i < 9; i++) {
            c.drawLine(startX, startY, endX, endY, black);
            startY += 100;
            endY += 100;
        }

        startX = 400;
        startY = 100;
        endX = 400;
        endY = 900;

        //horizontal lines
        for(int i = 0; i < 9; i++) {
            c.drawLine(startX, startY, endX, endY, black);
            startX += 100;
            endX += 100;
        }

        c.drawCircle(750, 450, 49, white);
        c.drawCircle(850, 550, 49, white);
        c.drawCircle(850, 450, 49, black);
        c.drawCircle(750, 550, 49, black);


        //The tiny circles on the board
        c.drawCircle(600, 300, 8, black);
        c.drawCircle(1000, 300, 8, black);
        c.drawCircle(600, 700, 8, black);
        c.drawCircle(1000, 700, 8, black);
    }
}
