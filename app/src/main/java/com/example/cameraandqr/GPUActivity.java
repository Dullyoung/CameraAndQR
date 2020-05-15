package com.example.cameraandqr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageAddBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorMatrixFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDirectionalSobelEdgeDetectionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHueBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageTransformFilter;

public class GPUActivity extends Activity {

    private GPUImage gpuImage;
    //显示处理结果
    private ImageView resultIv;
    Bitmap bitmap = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpu);
        resultIv = (ImageView) findViewById(R.id.resultIv);

        //获得Assets资源文件
        AssetManager as = getAssets();
        InputStream is = null;

        try {
            //注意名字要与图片名字一致
            is = as.open("timg.jpg");
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            Log.e("GPUImage", "Error");
        }

        // 使用GPUImage处理图像
        gpuImage = new GPUImage(this);
        gpuImage.setImage(bitmap);
        gpuImage.setFilter(new GPUImageDirectionalSobelEdgeDetectionFilter());
        bitmap = gpuImage.getBitmapWithFilterApplied();
        //显示处理后的图片
        resultIv.setImageBitmap(bitmap);


    }
}
