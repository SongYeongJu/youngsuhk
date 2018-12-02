package com.example.samsung.android_project.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsung.android_project.Adapter.PrecendentAdapter;
import com.example.samsung.android_project.DataStruct.Precedent;
import com.example.samsung.android_project.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class PrecedenceSearch extends AppCompatActivity {
    private TextView contentTextView;
    private Button searchButton;
    private EditText searchEditText;
    private ListView searchListView;
    private LinearLayout linearLayout;

    private ArrayList<Precedent> pList;

    private PrecendentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precedence_search);
        initObject();
    }

    private void initObject() {
        linearLayout=(LinearLayout)findViewById(R.id.themeLayout);
        linearLayout.setVisibility(View.GONE);

        contentTextView = (TextView) findViewById(R.id.contentTextView);
        searchEditText = (EditText) findViewById(R.id.searchEditText);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchListView = (ListView) findViewById(R.id.searchListView);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchEditText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"검색어를 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                getUrl();
                if (pList != null && pList.size() != 0) {
                    Log.d("song", "pList size : " + pList.size());
                    linearLayout.setVisibility(View.VISIBLE);
                    adapter=new PrecendentAdapter(pList);
                    searchListView.setAdapter(adapter);
                    searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(PrecedenceSearch.this,DetailedPreActivity.class);
                            intent.putExtra("info",adapter.getPre(position));
                            startActivity(intent);
                        }
                    });
                } else {
                    Log.d("song", "pList size : null ");
                }
            }
        });
    }

    private void getUrl() {

        StrictMode.enableDefaults();

        boolean[] checkInput = {false, false, false, false, false, false, false, false, false, false, false};
        String[] inputStr = {"", "", "", "", "", "", "", "", "", "", ""};

        try {
            pList = new ArrayList<Precedent>();
            URL url = new URL(
                    "http://www.law.go.kr/DRF/lawSearch.do?OC=test&target=prec&type=XML&mobileYn=Y&query="
                            +searchEditText.getText().toString()
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            contentTextView.setText(contentTextView.getText() + "에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        } else {
                            for (int i = 0; i < Precedent.contentName.length; i++) {
                                if (parser.getName().equals(Precedent.contentName[i])) { //eventNum 만나면 내용을 받을수 있게 하자
                                    checkInput[i] = true;
                                    break;
                                }
                            }
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        for(int i=0;i<checkInput.length;i++){
                            if (checkInput[i]) { //ineventNum true일 때 태그의 내용을 저장.
                                inputStr[i]+= parser.getText();
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("prec")) {
                            Precedent precedent = new Precedent();
                            for(int i=0;i<Precedent.contentName.length;i++){
                                precedent.setpContent(Precedent.contentName[i], inputStr[i]);
                            }
                            Log.d("song", "pre: " + precedent.toString());
                            pList.add(precedent);

                            for(int i=0;i<inputStr.length;i++){
                                inputStr[i]="";
                                checkInput[i] = false;
                            }
                        } else {
                            for (int i = 0; i < Precedent.contentName.length; i++){
                                if(parser.getName().equals(Precedent.contentName[i])){
                                    checkInput[i] = false;
                                }
                            }
                        }
                        break;
                }
                parserEvent = parser.next();
            }
            Log.d("song", "finish the parsing");
        } catch (Exception e) {
            pList = null;
            Log.d("song", "err to parsing");
        }
        if (pList == null) {
            contentTextView.setText("에러가..났습니다...");
        } else if (pList.size() == 0) {
            contentTextView.setText("판례가 없습니다.");
        } else {
            contentTextView.setVisibility(View.GONE);
        }
    }
}