package com.midas.performance;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.midas.performance.launchstarter.DelayInitDispatcher;
import com.midas.performance.memory.MemoryLeakActivity;
import com.midas.performance.memory.MemoryShakeActivity;
import com.midas.performance.net.RetrofitNewsUtils;
import com.midas.performance.service.JobSchedulerService;
import com.midas.performance.tasks.delayinittask.DelayInitTaskA;
import com.midas.performance.tasks.delayinittask.DelayInitTaskB;
import com.midas.performance.ui.TbsX5WebViewActivity;
import com.midas.performance.ui.adapter.NewsAdapter;
import com.midas.performance.ui.adapter.OnFeedShowCallBack;
import com.midas.performance.ui.bean.NewsItem;
import com.midas.performance.utils.LaunchTimer;
import com.midas.performance.utils.LogUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author midas
 */
//@Xml(layouts = "activity_main")
public class MainActivity extends AppCompatActivity implements OnFeedShowCallBack {

    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private String mStringIds = "20190220005233,20190220005171,20190220005160,20190220005146,20190220001228,20190220001227,20190219006994,20190219006839,20190219005350,20190219005343,20190219004522,20190219004520,20190219000132,20190219000118,20190219000119,20190218009367,20190218009078,20190218009075,20190218008572,20190218008496,20190218006078,20190218006156,20190218006190,20190218006572,20190218006235,20190218006284,20190218006571,20190218006283,20190218006191,20190218005733,20190217004740,20190218001891,20190218001889,20190217004183,20190217004019,20190217004011,20190217003152,20190217002757,20190217002249,20190217000954,20190217000957,20190217000953,20190216004269,20190216003721,20190216003720,20190216003351,20190216003364,20190216002989,20190216002613,20190216000044,20190216000043,20190216000042,20190215007933,20190215008945,20190215007932,20190215007090,20190215005473,20190215005469,20190215005313,20190215004868,20190215004299,20190215001233,20190215001229,20190215001226,20190214008652,20190214008429,20190214009262,20190214008347,20190214008345,20190214007362,20190214006949,20190214006948,20190214006588,20190214006270,20190214006102,20190214005769,20190214005583,20190214005581,20190214005484,20190214005466,20190214005303,20190214004660,20190213009703,20190213009285,20190214002912,20190213007775,20190213007461,20190213007049,20190213007047,20190213006228,20190213006050,20190213005767,20190213005738,20190213005641,20190213005512,20190213004174,20190212007918,20190212007914,20190212007913,20190212007696,20190212007369,20190212007361,20190212006921,20190212006007,20190212005954,20190212005925,20190212005924,20190212005923,20190212005922,20190212005428,20190212005427,20190212005426,20190212005226,20190212004916,20190212004422,20190212004355,20190212004351,20190212000989,20190212000994,20190212000991,20190211005672,20190211004121,20190211004049,20190211003973,20190211003434,20190211003199,20190211005392,20190211003179,20190211000956,20190211000955,20190211003203,20190211003206,20190210004201,20190210003934,20190210004067,20190210003683,20190210003685,20190210003684,20190210003682,20190210003281,20190210002944,20190210002936,20190210003308,20190210002745,20190210002634,20190210002893,20190210002315,20190210001977,20190210002046,20190210001663,20190209004408,20190209003643,20190209003582,20190209003401,20190209003193,20190209002777,20190209002664,20190209002724,20190209002723,20190209002119,20190208001691,20190208004370,20190208000203,20190208004129,20190208003560,20190208002739,20190208002661,20190208000144,20190208000194,20190208002671,20190208003081,20190208002398,20190208000184,20190208001943,20190208000074,20190208000051,20190208000121,20190207003938,20190207003939,20190208002394,20190207003698,20190207001759,20190207003882,20190207003424,20190207002872,20190207003101,20190207002873,20190207002772,20190207002036,20190207001888,20190207000695,20190206004239,20190206004172,20190206002264,20190206002238,20190206002237,20190206004192,20190206004176,20190206003738,20190206003028";
    public List<NewsItem> mItems = new ArrayList<>();

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
                        mRecyclerView = findViewById(R.id.recycler_view);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        mRecyclerView.setAdapter(mNewsAdapter);
                        mNewsAdapter.setOnFeedShowCallBack(MainActivity.this);
                    }
                });

        //从闪屏页主题 切换回App主题
        setTheme(R.style.AppTheme);
        mNewsAdapter = new NewsAdapter(mItems);
        getNews();
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

    /**
     * 演示JobScheduler的使用
     */
    private void startJobScheduler() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(getPackageName(),
                    JobSchedulerService.class.getName()));
            builder.setRequiresCharging(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
            jobScheduler.schedule(builder.build());
        }
    }

    /**
     * 获取数据
     */
    private void getNews() {
        RetrofitNewsUtils
                .getApiService()
                .getNBANews("banner", mStringIds)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String json = response.body().string();
                            JSONObject jsonObject = new JSONObject(json);
                            JSONObject data = jsonObject.getJSONObject("data");
                            Iterator<String> keys = data.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                JSONObject itemJO = data.getJSONObject(next);
                                NewsItem newsItem = JSON.parseObject(itemJO.toString(), NewsItem.class);
                                mItems.add(newsItem);
                            }
                            mNewsAdapter.setItems(mItems);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                    }
                });
    }

    @Override
    public void onFeedShow() {
        // 以下两行是原有方式
        //        new DispatchRunnable(new DelayInitTaskA()).run();
        //        new DispatchRunnable(new DelayInitTaskB()).run();

        //利用IdleHandler特性,空闲执行
        //对延迟任务进行分批初始化
        //每次只会执行一个task,即便是执行一个也是在系统空闲时间执行的
        DelayInitDispatcher delayInitDispatcher = new DelayInitDispatcher();
        delayInitDispatcher.addTask(new DelayInitTaskA())
                .addTask(new DelayInitTaskB())
                .start();

        // 一系列操作 10s
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, MemoryShakeActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, MemoryLeakActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, TbsX5WebViewActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 窗口显示的时候
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LaunchTimer.endRecord("窗口显示");
    }
}
