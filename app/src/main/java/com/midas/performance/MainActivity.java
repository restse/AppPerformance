package com.midas.performance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author midas
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //从闪屏页主题 切换回App主题
        setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_main);
    }
}
