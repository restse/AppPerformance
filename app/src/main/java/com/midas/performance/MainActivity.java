package com.midas.performance;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.midas.performance.utils.LogUtils;
import com.zhangyue.we.x2c.ano.Xml;

/**
 * @author midas
 */
@Xml(layouts = "activity_main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //获取每一个控件的耗时  super.onCreate(savedInstanceState); setContentView之前
        // setLayoutInflater();
        // setLayoutInflaterCompat();
        super.onCreate(savedInstanceState);
        //AsyncLayoutInflater  谷歌提供的 异步LayoutInflater (一个TextView 0~1ms)
        new AsyncLayoutInflater(MainActivity.this).inflate(R.layout.activity_main, null,
                new AsyncLayoutInflater.OnInflateFinishedListener() {
                    @Override
                    public void onInflateFinished(@NonNull View view, int i, @Nullable ViewGroup viewGroup) {
                        setContentView(view);
                    }
                });

        //从闪屏页主题 切换回App主题
        setTheme(R.style.AppTheme);
        // (一个TextView 172ms)
        // setContentView(R.layout.activity_main);
        // X2C 会转换为java文件  (一个TextView 100ms)
        //X2C.setContentView(this, R.layout.activity_main);
    }


    /**
     * LayoutInflater setFactory2
     * 可以实现例如批量更换某一个控件等的用途
     * 不能使用AsyncLayoutInflater
     */
    private void setLayoutInflater() {
        LayoutInflater.from(this).setFactory2(new LayoutInflater.Factory2() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

                if (TextUtils.equals(name, "TextView")) {
                    // 生成自定义TextView 判断如果 name 是 TextView 的话可以变成一个Button
                    Button button = new Button(MainActivity.this);
                    button.setText("LayoutInflater 我替换了TextView");
                    button.setAllCaps(false);
                    return button;
                }
                long time = System.currentTimeMillis();
                View view = getDelegate().createView(parent, name, context, attrs);
                LogUtils.i(name + "耗时 cost " + (System.currentTimeMillis() - time));
                return view;
            }

            @Override
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                return null;
            }
        });
    }

    /**
     * LayoutInflaterCompat setFactory2
     * 可以实现例如批量更换某一个控件等的用途
     * 不能使用AsyncLayoutInflater
     */
    private void setLayoutInflaterCompat() {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new LayoutInflater.Factory2() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

                if (TextUtils.equals(name, "TextView")) {
                    // 生成自定义TextView
                    Button button = new Button(MainActivity.this);
                    button.setText("LayoutInflaterCompat 我替换了TextView");
                    button.setAllCaps(false);
                    return button;
                }
                long time = System.currentTimeMillis();
                View view = getDelegate().createView(parent, name, context, attrs);
                LogUtils.i(name + "耗时 cost " + (System.currentTimeMillis() - time));
                return view;
            }

            @Override
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                return null;
            }
        });
    }
}
