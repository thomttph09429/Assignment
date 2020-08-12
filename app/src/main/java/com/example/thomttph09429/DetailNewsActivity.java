package com.example.thomttph09429;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thomttph09429.R;

public class DetailNewsActivity extends AppCompatActivity {
    WebView webView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        intent = getIntent();
        String link = intent.getStringExtra("linkURL");
        webView = findViewById(R.id.webview);
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}