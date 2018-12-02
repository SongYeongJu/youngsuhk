package com.example.samsung.android_project.Activity;

import android.content.Intent;
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

import com.example.samsung.android_project.Adapter.LawAdapter;
import com.example.samsung.android_project.DataStruct.Law;
import com.example.samsung.android_project.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class LawActivity extends AppCompatActivity {
    // Precedence Activity랑 같은 구조
    private ArrayList<Law> lList;

    private TextView contentTextView;
    private Button searchButton;
    private EditText searchEditText;
    private ListView searchListView;
    private LinearLayout linearLayout;

    private LawAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);
        init();
    }
    public void init(){
        linearLayout=(LinearLayout)findViewById(R.id.themeLayout1);
        linearLayout.setVisibility(View.GONE);

        contentTextView = (TextView) findViewById(R.id.contentTextView1);
        searchEditText = (EditText) findViewById(R.id.searchEditText1);
        searchButton = (Button) findViewById(R.id.searchButton1);
        searchListView = (ListView) findViewById(R.id.searchListView1);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUrl();
                if (lList!= null && lList.size() != 0) {
                    Log.d("song", "pList size : " + lList.size());
                    linearLayout.setVisibility(View.VISIBLE);

                    adapter=new LawAdapter(lList);

                    searchListView.setAdapter(adapter);
                    searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(LawActivity.this,DetailedLawActivity.class);
                            intent.putExtra("info",adapter.getLaw(position));
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

        boolean[] checkInput = {false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        String[] inputStr = {"","","","","","","","","","","","","",""};

        try {
            URL url;
            lList= new ArrayList<Law>();
            if(searchEditText.getText().toString()!=null && searchEditText.getText().toString().equals("")) {
                url = new URL(
                        "http://www.law.go.kr/DRF/lawSearch.do?OC=djfls0304&target=law&type=XML&mobileYn=Y&query="
                                +searchEditText.getText().toString()
                ); //검색 URL부분
            }else{
                url = new URL(
                        "http://www.law.go.kr/DRF/lawSearch.do?OC=djfls0304&target=law&type=XML&mobileYn=Y"
                ); //검색 URL부분
            }

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
                            for (int i = 0; i < Law.contentName.length; i++) {
                                if (parser.getName().equals(Law.contentName[i])) { //eventNum 만나면 내용을 받을수 있게 하자
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
                        if (parser.getName().equals("law")) {
                            Law law = new Law();
                            for(int i=0;i<Law.contentName.length;i++){
                                law.setContent(Law.contentName[i], inputStr[i]);
                            }
                            Log.d("song", "law: " + law.toString());
                            lList.add(law);

                            for(int i=0;i<inputStr.length;i++){
                                inputStr[i]="";
                                checkInput[i] = false;
                            }
                        } else {
                            for (int i = 0; i < Law.contentName.length; i++){
                                if(parser.getName().equals(Law.contentName[i])){
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
            lList= null;
            Log.d("song", "err to parsing");
        }
        if (lList == null) {
            contentTextView.setText("에러가..났습니다...");
        } else if (lList.size() == 0) {
            contentTextView.setText("판례가 없습니다.");
        } else {
            contentTextView.setVisibility(View.GONE);
        }
    }

}
