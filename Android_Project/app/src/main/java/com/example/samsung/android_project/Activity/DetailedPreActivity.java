package com.example.samsung.android_project.Activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.samsung.android_project.DataStruct.Precedent;
import com.example.samsung.android_project.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class DetailedPreActivity extends AppCompatActivity {
    // 판례 상세정보 표시 액티비티
    private Precedent precedent;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_pre);
        try{
            //앞의 액티비티에서 넘겨준 판례 객체 가져옴
            Intent intent=getIntent();
            precedent=(Precedent)intent.getExtras().get("info");
            Log.d("song","success to load precedent");

            // 웹뷰 init
            webView=(WebView)findViewById(R.id.webView);
            WebSettings webSettings=webView.getSettings();

            webView.getSettings().setJavaScriptEnabled(true);
            // 웹뷰 url에 판례 상세정보 불러오는 url 지정 -> 반환 형식 html이라 웹뷰 사용함
            webView.loadUrl(
                    "http://www.law.go.kr/DRF/lawService.do?OC=djfls0304&target=prec&type=HTML&mobileYn=Y&ID="
                    + precedent.getPContent(0)
            );
            // WebViewClient 지정
            webView.setWebViewClient(new WebViewClientClass());
        }catch (Exception e) {
            Log.d("song","fail to load precedent");
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
