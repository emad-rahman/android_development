package com.example.emadrahman.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSecondActivityBtn();
    }

    public void setSecondActivityBtn(){
        Button btn = (Button) findViewById(R.id.secondActivityBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked This Button", Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Intent intent = SecondActivity.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }
}
