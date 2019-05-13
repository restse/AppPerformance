package com.midas.performance.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.midas.performance.R;
import com.midas.performance.image.ImageCache;
import com.midas.performance.ui.adapter.ImageCacheAdapter;


/**
 * @author midas
 * <p>
 * 图片缓存
 */
public class ImageCacheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cache);
        ImageCache.getInstance().init(this, Environment.getExternalStorageDirectory() + "/midas");

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new ImageCacheAdapter(this));
        //假设是从网络上来的
//        BitmapFactory.Options options=new BitmapFactory.Options();
//        //如果要复用，需要设计成异变
//        options.inMutable=true;
//        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.wyz_p,options);
//        for(int i=0;i<100;i++){
//            options.inBitmap=bitmap;
//            bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.wyz_p,options);
//        }

    }

    void i(Bitmap bitmap) {
        Log.i("midas", "图片" + bitmap.getWidth() + "x" + bitmap.getHeight() + " 内存大小是:" + bitmap.getByteCount());
    }
}
