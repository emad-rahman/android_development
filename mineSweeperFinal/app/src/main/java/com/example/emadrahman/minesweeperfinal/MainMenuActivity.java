package com.example.emadrahman.minesweeperfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        linkPlayGameButton();
        linkOptionsButton();
    }

    private void linkPlayGameButton() {
        Button btn = (Button) findViewById(R.id.button_play_game);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = PlayGameActivity.makeIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    private void linkOptionsButton() {
        Button btn = (Button) findViewById(R.id.button_options);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OptionsActivity.makeIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }
}
