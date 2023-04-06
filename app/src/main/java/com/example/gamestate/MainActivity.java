// @authors - Hung-Nghi Vu, Malory Morey, Masato Tsuji
// @version - 3/17/2023
package com.example.gamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.View);
        Controller controller = new Controller((com.example.gamestate.View) view);

        view.setOnTouchListener(controller);
    }

    @Override
    public void onClick(View view) {
        //clear text in text edit
        et.getText().clear();
        //create a game state instance
        GameState firstInstance = new GameState();
        //Create a deep copy of firstInstance from the perspective of player one.
        // Assign this copy to a variable named secondInstance.
        GameState secondInstance = new GameState(firstInstance);
        //use every method
        firstInstance.getNumBlackPieces();
        firstInstance.getNumWhitePieces();
        firstInstance.getIsBlackTurn();
        firstInstance.getGameOver();
        firstInstance.getBoard();
        //
        et.setText("Black has " + firstInstance.getNumBlackPieces() + "."
                + "White has " + firstInstance.getNumWhitePieces() + "."
                + "It is Blacks turn (" + firstInstance.getIsBlackTurn() + ")."
                + "The game is over (" + firstInstance.getGameOver() + ").");

        //Create a game state instance
        GameState thirdInstance = new GameState();

        //Create a deep copy of firstInstance from the perspective of player one.
        // Assign this copy to a variable named secondInstance.
        GameState fourthInstance = new GameState(thirdInstance);

        //Call the toString() method on secondInstance and fourthInstance.
        secondInstance.toString();
        fourthInstance.toString();

    }
}