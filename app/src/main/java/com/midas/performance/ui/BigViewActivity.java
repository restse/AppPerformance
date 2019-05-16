package com.midas.performance.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.midas.performance.R;
import com.midas.performance.image.BigView;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 *@author Dell
 *@date 2019/5/16
 *
 * 加载大图
 */
public class BigViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_view);

        BigView bigView = findViewById(R.id.big_view);
        InputStream is = null;

        try {
            is = getAssets().open("big.png");
            bigView.setImage(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
