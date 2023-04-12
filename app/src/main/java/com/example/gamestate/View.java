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


    private GameState gameState;

    int startX;
    int startY;
    int endX;
    int endY;

    Paint black = new Paint();
    Paint white = new Paint();
    Paint green = new Paint();
    Paint sage = new Paint();
    Paint othello = new Paint();
    Paint brownBox = new Paint();

    public View(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameState = new GameState();

        black.setColor(0xFF000000);
        white.setColor(0xFFFFFFFF);
        green.setColor(0xFF29AB70);
        sage.setColor(0xff65a765);
        othello.setColor(Color.WHITE);
        othello.setStyle(Paint.Style.FILL);
        brownBox.setColor(0xFFceb396);

        setBackgroundColor(Color.WHITE);

    }
    public GameState getGameState(){ return gameState;}

    public void onDraw(Canvas c) {
        super.onDraw(c);
        startX = 400;
        startY = 100;
        endX = 1200;
        endY = 100;

        if(gameState.homeScreen){
            c.drawPaint(othello);
            c.drawRect(400, 100, 1200, 900, sage);
            black.setTextSize(150);
            c.drawText("OTHELLO", 485, 250, black);
            black.setTextSize(125);
            c.drawText("START GAME", 415, 350, black);

            //Boxes
            c.drawRect(525, 600, 725, 675, brownBox);
            c.drawRect(875, 600, 1075, 675, brownBox);

            //Human or AI
            black.setTextSize(50);
            c.drawText("Human", 545, 655, black);
            black.setTextSize(40);
            c.drawText("Computer", 885, 655, black);
            if(gameState.AIGame){
                //Easy or GodMode
                //Boxes
                c.drawRect(525, 600, 725, 675, brownBox);
                c.drawRect(875, 600, 1075, 675, brownBox);
                black.setTextSize(50);
                c.drawText("Easy", 565, 655, black);
                black.setTextSize(40);
                c.drawText("God-Mode", 885, 655, black);
            }

        }
        else {
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
            for (int i = 0; i < 9; i++) {
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

            //Check the board and update it with the current state of the game
            char[][] state = gameState.getBoard();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (state[i][j] == 'b') {
                        c.drawCircle(450 + (100 * j), 150 + (100 * i), 49, black);
                    } else if (state[i][j] == 'w') {
                        c.drawCircle(450 + (100 * j), 150 + (100 * i), 49, white);
                    } else if (state[i][j] == 'e') {
                        c.drawCircle(450 + (100 * j), 150 + (100 * i), 49, green);
                    }
                }
            }
        }

        //sleep(2000);
        //AI Move with delay
        //gameState.dumbAIMove();
        /*for(int i = 0; i < 8; i++){
            for(int j = 0; j< 8; j++){
                if(state[i][j] == 'w') {
                    c.drawCircle(450 + (100 * i), 150 + (100 * j), 49, white);
                }
            }
        }*/
        //gameState.setIsBlackTurn(true);

        }
    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }
    }
    }

