package com.magere.domah;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.magere.domah.utils.NotificationReceiver;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        class Android{

            @SuppressLint("CheckResult")
            @JavascriptInterface
            public void showToast(String message){
                Observable.just(message)
                        .map(String::hashCode)
                        .map(i -> Integer.toString(i))
                        .subscribe(s  -> Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show());
                startBroadcast();
            }
        }

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new Android(), "Android");
        webView.loadUrl("https://testmamagit.github.io");
    }

    private void startBroadcast(){
        Intent intent = new Intent("com.magere.domah.startBroadcast");
        sendBroadcast(intent);
    }
}
