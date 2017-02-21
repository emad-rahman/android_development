package com.example.emadrahman.minesweeperfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class PlayGameActivity extends AppCompatActivity {
    private static int NUM_ROWS;
    private static int NUM_COLS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        
        setRowAndCol();
        createTableOfMines();
    }

    private void setRowAndCol() {
        NUM_ROWS = OptionsActivity.getGridSizeRow(this);
        NUM_COLS = OptionsActivity.getGridSizeCol(this);
    }

    private void createTableOfMines() {
        TableLayout table = (TableLayout) findViewById(R.id.table_mine_seeker);
        for(int row = 0; row < NUM_ROWS; row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            for(int col = 0; col < NUM_COLS; col++){
                Button btn = new Button(this);
                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                btn.setPadding(0,0,0,0);
                tableRow.addView(btn);

            }
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, PlayGameActivity.class);
    }
}
