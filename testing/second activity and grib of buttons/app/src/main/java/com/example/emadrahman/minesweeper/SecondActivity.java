package com.example.emadrahman.minesweeper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setUpEndActivityButton();
    }

    private void setUpEndActivityButton() {
        Button btn = (Button) findViewById(R.id.end_activity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "Ending second activity", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    public static Intent makeIntent(Context context){
        return new Intent(context, SecondActivity.class);
    }
}
