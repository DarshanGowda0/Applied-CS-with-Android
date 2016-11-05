package com.dark.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int userOverallScore = 0, userTurnScore = 0;
    public int computerOverallScore = 0, computerTurnScore = 0;

    Button rollButton,holdButton;
    TextView label;
    ImageView imageView;

    String userScoreLabel = "<b><i>Your score : </i></b>";
    String compScoreLabel = "<b><i> Computer score : </i></b>";
    String userTurnScoreLabel = "<b><i> Your turn score : </i></b>";
    String compTurnScoreLabel = "\n<b><i>computer turn score : </i></b>";

    String labelText = userScoreLabel + userOverallScore + compScoreLabel + computerOverallScore;

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
}
