package com.example.gamestate;

import android.content.Context;
import android.graphics.Paint;
import android.view.SurfaceView;

public class View extends SurfaceView {


    private Model model = new Model();


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

}
