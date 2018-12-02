package com.example.samsung.android_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.samsung.android_project.R;

public class MainActivity extends AppCompatActivity {

    private Button law;
    private Button pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        law = (Button) findViewById(R.id.lawButton);
        law.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,LawActivity.class);
                        startActivity(intent);
                    }
                }
        );
        pre = (Button) findViewById(R.id.preButton);
        pre.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,PrecedenceSearch.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
