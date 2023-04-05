// @authors - Hung-Nghi Vu, Malory Morey, Masato Tsuji
// @version - 3/17/2023
package com.example.gamestate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class View extends SurfaceView {


    private Model model = new Model();

    int startX;
    int startY;
    int endX;
    int endY;

    Paint black = new Paint();
    Paint white = new Paint();
    Paint green = new Paint();

    public View(Context context) {
        super(context);
        model = new Model();

        black.setColor(0x00000000);
        white.setColor(0xFFFFFFFF);
        green.setColor(0xFF29AB87);

    }

    public void onDraw(Canvas c) {
        super.onDraw(c);
        startX = 0;
        startY = 0;
        endX = 100;
        endY = 0;

        c.drawRect(0, 0, 100, 100, green);
        for (int i = 0; i < 9; i++) {
            c.drawLine(startX, startY, endX, endY, black);
            startY += 10;
            endY += 10;
        }
        startX = 0;
        startY = 0;
        endX = 0;
        endY = 100;

        for(int i = 0; i < 9; i++) {
            c.drawLine(startX, startY, endX, endY, black);
            startX += 10;
            endX += 10;

        }
    }
}
