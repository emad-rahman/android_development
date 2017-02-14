package com.example.emadrahman.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView rollResult;
    Button resetButton;

    // Field to hold the score
    int score;
    // Field to hold random number
    Random rand;
    //Field to hold the score text
    TextView scoreText;
    //Fields to hold die values
    int die1;
    int die2;
    int die3;

    //List to hold dice
    List<Integer> dice;

    //List to hold image views
    List<ImageView> diceImageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Dice Out Game");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        //adding comment to see if git is working
        score = 0;

        Toast.makeText(getApplicationContext(), "Welcome to Dice Out!", Toast.LENGTH_SHORT).show();

        rollResult = (TextView) findViewById(R.id.rollResult);

        // Instantiate random number generator
        rand = new Random();

        //create arraylist container for the dice values
        dice = new ArrayList<Integer>();

        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);

        scoreText = (TextView) findViewById(R.id.scoreText);

        resetButton = (Button) findViewById(R.id.resetButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void rollDice(View v){
        rollResult.setText("Clicked!");

        //roll dice
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;

        //set dice value into arraylist
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        //iterate through the list of dice and get the dice value in the form of the name
        //of the image file. eg die_1.png
        for(int i = 0; i < 3; i++){
            String imageName = "die_" + dice.get(i) + ".png";

            try{
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(i).setImageDrawable(d);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        //Build message with the result
        String msg;

        if (die1 == die2 && die1 == die3){
            //triples
            int scoreDelta = die1 * 100;
            msg = "You rolled a triple " + die1 + "! You score " + scoreDelta + " points!";
            score += scoreDelta;
        }else if(die1 == die2 || die1 == die3 || die2 == die3){
            msg = "You rolled doubles for 50 points!";
            score += 50;
        }else {
            msg = "You didn't score anything this roll. Try again!";
        }

        //update the app to display the result of message
        rollResult.setText(msg);
        scoreText.setText("Score: " + score);
    }

    public void resetScore(View v){
        score = 0;
        String msg = "Resetting";

        for(int i = 0; i < 3; i++){
            try{
                InputStream stream = getAssets().open("die_1.png");
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(i).setImageDrawable(d);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        rollResult.setText(msg);
        scoreText.setText("Score: " + score);


    }
}
