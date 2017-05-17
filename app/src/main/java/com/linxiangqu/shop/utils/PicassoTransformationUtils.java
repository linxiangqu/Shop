package com.linxiangqu.shop.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Created by linxiangqu on 2016/8/12.
 */
public class PicassoTransformationUtils implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {

        //获取最小边长
        int size = Math.min(source.getWidth(), source.getHeight());

        //获取圆形图片的宽度和高度
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        //创建一个正方形区域的Btimap
        Bitmap squaredBitmap = Bitmap.createBitmap(source, 0, 0, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        //创建一张可以操作的正方形图片的位图
        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        //创建一个画布Canvas
        Canvas canvas = new Canvas(bitmap);
        //创建画笔
        Paint paint = new Paint();

        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        //圆形半径
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

//        Paint paint1 = new Paint();
//        paint1.setShader(shader);
//        paint1.setAntiAlias(true);
//        paint1.setStrokeWidth(5f);
//        paint1.setColor(0xff424242);
//        canvas.drawCircle(r,r,r-2,paint1);
        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "success";
    }
}
