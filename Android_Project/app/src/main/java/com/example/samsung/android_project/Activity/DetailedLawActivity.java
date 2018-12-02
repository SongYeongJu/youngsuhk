package com.example.samsung.android_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.samsung.android_project.DataStruct.Law;
import com.example.samsung.android_project.DataStruct.Precedent;
import com.example.samsung.android_project.R;

public class DetailedLawActivity extends AppCompatActivity {

    private Law law;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_law);
        try{
            Intent intent=getIntent();
            law=(Law) intent.getExtras().get("info");
            Log.d("song","success to load law");
            Log.d("song","law : "+ law.toString());

            webView=(WebView)findViewById(R.id.webView1);
            WebSettings webSettings=webView.getSettings();

            webView.getSettings().setJavaScriptEnabled(true);
            // 구글홈페이지 지정
            webView.loadUrl(
                    "http://www.law.go.kr/DRF/lawService.do?OC=djfls0304&target=law&type=HTML&mobileYn=Y&ID="
                            + law.getContent(4)
            );
            // WebViewClient 지정
            webView.setWebViewClient(new DetailedLawActivity.WebViewClientClass1());
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

    private class WebViewClientClass1 extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
