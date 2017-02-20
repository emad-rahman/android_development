package com.example.emadrahman.radiobuttonandsettings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupOptionsButton();
        refreshScreen();
    }


    private void setupOptionsButton() {
        Button button = (Button) findViewById(R.id.options_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OptionsActivity.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }


    private void refreshScreen() {
        //refresh numPanels display
        TextView tvNumPanels = (TextView) findViewById(R.id.num_panels_installed);
        int numPanels = OptionsActivity.getNumPanelsInstalled(this);
        tvNumPanels.setText("" + numPanels);

        //Set the cost
        TextView tvCost = (TextView) findViewById(R.id.cost_of_panels);
        int cost = numPanels * 1000;
        tvCost.setText("$" + cost);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshScreen();
    }
}
