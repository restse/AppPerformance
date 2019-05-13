package com.midas.performance.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.midas.performance.R;
import com.midas.performance.image.ImageCache;
import com.midas.performance.image.ImageResize;

/**
 * @author midas
 *
 * 图片adapter
 */
public class ImageCacheAdapter extends BaseAdapter {
    private Context context;

    public ImageCacheAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 999;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_image_cache, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //第一次优化
//        Bitmap bitmap = ImageResize.resizeBitmap(context, R.mipmap.wyz_p,
//                80, 80, false);

        Bitmap bitmap= ImageCache.getInstance().getBitmapFromMemory(String.valueOf(position));
        if(null==bitmap){
            //如果内存没数据，就去复用池找
            Bitmap reusable=ImageCache.getInstance().getReusable(80,80,1);
            //reusable能复用的内存
            //从磁盘找
            bitmap = ImageCache.getInstance().getBitmapFromDisk(String.valueOf(position),reusable);
            //如果磁盘中也没缓存,就从网络下载
            if(null==bitmap){
                bitmap= ImageResize.resizeBitmap(context,R.drawable.ycy,100,100,false,reusable);
                ImageCache.getInstance().putBitmapToMemory(String.valueOf(position),bitmap);
                ImageCache.getInstance().putBitMapToDisk(String.valueOf(position),bitmap);
                Log.i("midas---","从网络加载了数据");
            }else{
                Log.i("midas---","从磁盘中加载了数据");
            }

        }else{
            Log.i("midas---","从内存中加载了数据");
        }


        holder.iv.setImageBitmap(bitmap);
        return convertView;
    }

    class ViewHolder {
        ImageView iv;
        ViewHolder(View view) {
            iv = view.findViewById(R.id.iv);
        }
    }
}
