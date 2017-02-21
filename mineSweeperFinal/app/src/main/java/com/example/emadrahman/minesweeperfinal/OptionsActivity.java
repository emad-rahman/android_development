package com.example.emadrahman.minesweeperfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        generateRadioButtonsGridSize();
        generateRadioButtonsNumMines();
    }

    private void generateRadioButtonsNumMines() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_num_mines);
        int[] numOfMines = getResources().getIntArray(R.array.num_of_mines);

        for (int i = 0; i < numOfMines.length; i++){
            final int numOfMine = numOfMines[i];

            RadioButton btn = new RadioButton(this);
            btn.setText(numOfMine + " mines");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setNumMines(numOfMine);
                }
            });
            radioGroup.addView(btn);

            if(numOfMine == getNumMines(this)){
                btn.setChecked(true);
            }
        }
    }

    private void setNumMines(int numOfMine) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Number of Mines ", numOfMine);
        editor.apply();
    }

    public static int getNumMines(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("Number of Mines ", context.getResources().getInteger(R.integer.default_num_of_mines));
    }

    private void generateRadioButtonsGridSize() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_grid_size);

        int[] rowOptions = getResources().getIntArray(R.array.grid_size_row);
        int[] colOptions = getResources().getIntArray(R.array.grid_size_col);

        for (int i = 0; i < rowOptions.length; i++){
            final int row = rowOptions[i];
            final int col = colOptions[i];
            RadioButton btn = new RadioButton(this);
            btn.setText(row + " x " + col);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveGridSize(row, col);
                }
            });
            radioGroup.addView(btn);

            if(row == getGridSizeRow(this) && col == getGridSizeCol(this)){
                btn.setChecked(true);
            }
        }
    }

    private void saveGridSize(int row, int col) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Grid Size Row ", row);
        editor.putInt("Grid Size Col ", col);
        editor.apply();
    }

    public static int getGridSizeRow(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("Grid Size Row ", context.getResources().getInteger(R.integer.default_grid_row));
    }

    public static int getGridSizeCol(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("Grid Size Col ", context.getResources().getInteger(R.integer.default_grid_col));
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, OptionsActivity.class);
    }
}
