package com.midas.performance.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.midas.performance.R;
import com.midas.performance.webview.X5WebView;
import com.tencent.smtt.sdk.WebChromeClient;


/**
 * @author midas
 *
 * TbsX5WebView
 */
public class TbsX5WebViewActivity extends AppCompatActivity {

    private String mWebUrl;
    private String mWebTitle;

    private X5WebView x5WebView;

    String url = "http://news.youth.cn/gn/201904/t20190428_11939646.htm";

    /**
     * actionStart
     */
    public static void actionStart(Context context, String title, String url) {
        Intent intent = new Intent(context, TbsX5WebViewActivity.class);
        intent.putExtra("X5WebTitle", title);
        intent.putExtra("X5WebUrl", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbs_x5_web_view);
        //x5 播放视频为了避免闪屏和透明问题
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        mWebTitle = getIntent().getStringExtra("X5WebTitle");
        mWebUrl = getIntent().getStringExtra("X5WebUrl");
        initView();
    }

    /**
     * initView
     */
    private void initView() {
        x5WebView = findViewById(R.id.x5_web_view);
        x5WebView.loadUrl(url);
        x5WebView.setWebChromeClient(new WebChromeClient() {

        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && x5WebView.canGoBack()) {
            x5WebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
