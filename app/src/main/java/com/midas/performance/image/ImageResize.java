package com.midas.performance.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author midas
 * <p>
 * 图片大小缩放
 */
public class ImageResize {

    /**
     * 缩放bitmap
     *
     * @param context
     * @param id
     * @param maxW
     * @param maxH
     * @return
     */
    public static Bitmap resizeBitmap(Context context, int id, int maxW, int maxH, boolean hasAlpha, Bitmap reusable) {
        Resources resources = context.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 只解码出 outxxx参数 比如 宽、高
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, id, options);
        //根据宽、高进行缩放
        int w = options.outWidth;
        int h = options.outHeight;
        //设置缩放系数
        options.inSampleSize = calculateInSampleSize(w, h, maxW, maxH);
        if (!hasAlpha) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        options.inJustDecodeBounds = false;
        //设置成能复用
        options.inMutable = true;
        options.inBitmap = reusable;
        return BitmapFactory.decodeResource(resources, id, options);
    }

    /**
     * 计算缩放系数
     *
     * @param w
     * @param h
     * @param maxW
     * @param maxH
     * @return 缩放的系数
     */
    private static int calculateInSampleSize(int w, int h, int maxW, int maxH) {
        int inSampleSize = 1;
        if (w > maxW && h > maxH) {
            inSampleSize = 2;
            //循环 使宽、高小于 最大的宽、高
//            while (w / inSampleSize > maxW && h / inSampleSize > maxH) {
//                inSampleSize *= 2;
//            }
            do {
                inSampleSize *= 2;
            } while (w / inSampleSize > maxW && h / inSampleSize > maxH);
        }
       // inSampleSize /= 2;
        return inSampleSize;
    }



    public static Bitmap resizeBitmap(Context context, int id, int maxW, int maxH, boolean hasAlpha) {
        Resources resources = context.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        //需要拿得到系统处理的信息  比如解码出宽高,....
        options.inJustDecodeBounds = true;
        //我们把原来的解码参数改了再去生成bitmap
        BitmapFactory.decodeResource(resources, id, options);
        //取到宽高
        int w = options.outWidth;
        int h = options.outHeight;
        //设置缩放系数
        options.inSampleSize = calculateInSampleSize(w, h, maxW, maxH);

        if(!hasAlpha){
            options.inPreferredConfig=Bitmap.Config.RGB_565;
        }
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(resources,id,options);


    }
}
