package com.dark.scarnesdice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TEST_TAG";


    public int userOverallScore = 0, userTurnScore = 0;
    public int computerOverallScore = 0, computerTurnScore = 0;

    Button rollButton, holdButton;
    TextView label;
    ImageView imageView;

    String userScoreLabel = "<b><i>Your score : </i></b>";
    String compScoreLabel = "<b><i> Computer score : </i></b>";
    String userTurnScoreLabel = "<b><i> Your turn score : </i></b>";
    String compTurnScoreLabel = "\n<b><i>computer turn score : </i></b>";

    String labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore;

    int[] drawables = {R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollButton = (Button) findViewById(R.id.roll);
        holdButton = (Button) findViewById(R.id.hold);
        label = (TextView) findViewById(R.id.label);
        imageView = (ImageView) findViewById(R.id.imageView);

        label.setText(Html.fromHtml(labelText));

    }

    public void rollButtonClick(View view) {

        int rolledNumber = rollDice();
        imageView.setImageResource(drawables[rolledNumber]);

        rolledNumber++;

        if (rolledNumber == 1) {
            userTurnScore = 0;
            labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + userTurnScoreLabel + userTurnScore + "\n you lost your chance";
            computerTurn();
        } else {
            userTurnScore += rolledNumber;
            labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore + userTurnScoreLabel + userTurnScore;
        }
        label.setText(Html.fromHtml(labelText));

    }

    private void computerTurn() {
        
    }

    public void holdButtonClick(View view) {

    }

    public void resetButtonClick(View view) {

    }

    private int rollDice() {

        Random random = new Random();
        int randomNumber = random.nextInt(6);
        Log.d(TAG, "rollBtnClick: " + randomNumber);

        return randomNumber;

    }
}
