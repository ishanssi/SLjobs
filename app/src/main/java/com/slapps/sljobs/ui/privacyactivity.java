package com.slapps.sljobs.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.slapps.sljobs.R;

public class privacyactivity extends AppCompatActivity {
    static WebView wv;
    public static ProgressDialog progressDialogload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacyactivity);


        wv = (WebView) findViewById(R.id.webviewpivacy);


        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= 19) {
            wv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            wv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        wv.loadUrl("file:///android_asset/privacyfile");
        // now it will not fail here
    }


}