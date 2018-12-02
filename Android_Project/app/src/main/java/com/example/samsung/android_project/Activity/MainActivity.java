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

        //법률 검색 액티비티로 넘어가는 버튼
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
        //판례 검색 액티비티로 넘어가는 버튼
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
