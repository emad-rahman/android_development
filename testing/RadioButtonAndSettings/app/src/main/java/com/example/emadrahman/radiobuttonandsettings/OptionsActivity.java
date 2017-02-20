package com.example.emadrahman.radiobuttonandsettings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        createRadioButtons();

        int savedValue =getNumPanelsInstalled(OptionsActivity.this);
        Toast.makeText(OptionsActivity.this, "Saved Value: " + savedValue, Toast.LENGTH_SHORT).show();
    }

    private void createRadioButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_install_size);

        int[] numPanels = getResources().getIntArray(R.array.num_solar_panels);


        //create the buttons
        for(int i = 0; i < numPanels.length; i++){
            final int numPanel = numPanels[i];

            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.solar_panels, numPanel));

            //set on-click call backs
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionsActivity.this, "You clicked " + numPanel, Toast.LENGTH_SHORT).show();
                    
                    saveNumPanelsInstalled(numPanel);
                }
            });

            //Add to radio group
            group.addView(button);

            //select default button
            if (numPanel == getNumPanelsInstalled(this)){
                button.setChecked(true);
            }
        }

    }

    private void saveNumPanelsInstalled(int numPanel) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Num installed panels ", numPanel);
        editor.apply();
    }

    public static int getNumPanelsInstalled(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("Num installed panels ", context.getResources().getInteger(R.integer.default_num_solar_panels));
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, OptionsActivity.class);
    }
}
