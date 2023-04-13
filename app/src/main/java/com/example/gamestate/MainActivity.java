// @authors - Hung-Nghi Vu, Malory Morey, Masato Tsuji
// @version - 4/13/2023

//Beta Notes - The game currently supports the rules of Othello as discussed in the requirements presentation.
//          - This includes black going first, valid moves where opposite discs are surrounded between the piece you
//          place and another you own, discs are flipped when a valid move is performed, games ending when board is full
//          No valid moves left, player skips turn if no valid move is found, player must play if valid move is available.
//          - GUI for title screen, board screen, and game-over screen work as intended.
//          - Buttons and places on board clearly marked for users.
//          - Graphics are in final form.
//          - All combinations can be played (1 on 1, 1 on AI).
//          - AIs discussed in requirements are modified to establish a discrepancy between dumb and smart.
//          - Our dumb AI looks from the top row from left to right, and moves to the next row below until
//          a valid move is found. It will make the first valid move it finds. This is different from random as
//          it would be possibly as effective as our smart one.
//          - Our GodMode AI prioritises the corners, as those spots will guarantee a spot. Then spots at
//          the edge of the board (row = 0/8 or col = 0/8), as those spots are less likely to be stolen.
//          Lastly, if those moves aren't available, it will do moves near the center, the least desirable spots, as
//          they are most likely to be captured. This AI is still in development, as sometimes it doesn't take an
//          edge or corner when it is available. But for the most part, it performs more effectively than the dumb AI.
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