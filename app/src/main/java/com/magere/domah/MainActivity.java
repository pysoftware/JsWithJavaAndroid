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

import java.util.Map;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    Map<String, String> map = null;

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
//                Observable.just(message)
//                        .map(String::hashCode)
//                        .map(i -> Integer.toString(i))
//                        .subscribe(s  -> Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show())
                startBroadcast(message);
            }
        }

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new Android(), "Android");
        webView.loadUrl("https://testmamagit.github.io");
    }

    private void startBroadcast(String message){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("text/plain");

        Intent chooser = Intent.createChooser(intent, "Choose app: ");

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(chooser);
        }
//        sendBroadcast(intent);
    }
}
