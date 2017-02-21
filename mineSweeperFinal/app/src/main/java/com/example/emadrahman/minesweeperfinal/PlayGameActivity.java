package com.example.emadrahman.minesweeperfinal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

public class PlayGameActivity extends AppCompatActivity {
    private static int NUM_ROWS;
    private static int NUM_COLS;
    private static int NUM_MINES;

    Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        
        setUp();
        //randomlySetMines();
        createTableOfMines();
    }

    private void setUp() {
        NUM_ROWS = OptionsActivity.getGridSizeRow(this);
        NUM_COLS = OptionsActivity.getGridSizeCol(this);
        NUM_MINES = OptionsActivity.getNumMines(this);
        buttons = new Button[NUM_ROWS][NUM_COLS];
    }

    private void createTableOfMines() {
        TableLayout table = (TableLayout) findViewById(R.id.table_mine_seeker);
        for(int row = 0; row < NUM_ROWS; row++){
            final int final_row = row;
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            for(int col = 0; col < NUM_COLS; col++){
                final int final_col = col;
                Button btn = new Button(this);
                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                btn.setPadding(0,0,0,0);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(final_row, final_col);
                    }
                });
                tableRow.addView(btn);
                buttons[row][col] = btn;
            }
        }


    }

    private void randomlySetMines(){
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < NUM_MINES){
            Button btn = buttons[rand.nextInt(NUM_ROWS)][rand.nextInt(NUM_COLS)];
            if(!btn.getBackground().getConstantState().equals(R.drawable.smiles)) {
                Log.i("width", "width = "+btn.getWidth());
                Log.i("height", "height = "+btn.getHeight());
                setButtonBackground(btn);
                minesPlaced++;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        Button btn = buttons[row][col];
        //lockButtonSizes();
        setButtonBackground(btn);
    }

    private void setButtonBackground(Button btn) {
        lockButtonSizes();
        int newWidth = btn.getWidth();
        int newHeight = btn.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.smiles);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        btn.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }

    private void lockButtonSizes() {
        for(int row = 0; row < NUM_ROWS; row++){
            for (int col = 0; col < NUM_COLS; col++){
                Button btn = buttons[row][col];

                int width = btn.getWidth();
                btn.setMinWidth(width);
                btn.setMaxWidth(width);

                int height = btn.getHeight();
                btn.setMinHeight(height);
                btn.setMaxHeight(height);
            }
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, PlayGameActivity.class);
    }
}
