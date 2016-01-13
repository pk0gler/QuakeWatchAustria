package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.quakewatch.ekos.quakewatchaustria.R;

public class SubActivity_Guide extends AppCompatActivity {
    ProgressDialog pd;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_activity__guide);
        webView = (WebView) findViewById(R.id.webapp);
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait Loading...");
        pd.show();
        webView.loadUrl("http://www.zamg.ac.at/cms/de/geophysik/erdbeben/verhaltensratgeber/verhalten-in-oesterreich");
        webView.setWebChromeClient(new WebChromeClient());
        WebViewClient myClient = new WebClient();
        webView.setWebViewClient(myClient);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);

        //prog = (ProgressBar) findViewById(R.id.prog);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_activity__guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                if (webView.getUrl().equals("http://www.zamg.ac.at/cms/de/geophysik/erdbeben/verhaltensratgeber/verhalten-in-oesterreich"))
                    finish();
                webView.goBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class WebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            pd.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
        }

    }

}
