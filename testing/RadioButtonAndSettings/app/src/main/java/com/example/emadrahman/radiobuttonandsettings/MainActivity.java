package com.example.emadrahman.radiobuttonandsettings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createRadioButtons();
        setupPrintSelected();
    }

    private void setupPrintSelected() {

        Button button = (Button) findViewById(R.id.find_selected);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_install_size);
                int getIdOfSelected = group.getCheckedRadioButtonId();

                RadioButton radioButton = (RadioButton) findViewById(getIdOfSelected);
                String message = radioButton.getText().toString();

                Toast.makeText(MainActivity.this, "Selected button text: "+ message, Toast.LENGTH_SHORT).show();
            }
        });
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
                    Toast.makeText(MainActivity.this, "You clicked " + numPanel, Toast.LENGTH_SHORT).show();
                }
            });

            //Add to radio group

            group.addView(button);
        }


    }
}
