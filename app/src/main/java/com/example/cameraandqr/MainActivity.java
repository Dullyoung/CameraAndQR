package com.example.cameraandqr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.qr)
    Button qr;
    @BindView(R.id.LJ)
    Button LJ;
    @BindView(R.id.multiSelect)
    Button multiSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String  src="";
        if (requestCode == 666 && resultCode == RESULT_OK) {
            ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            for (String s :
                    path) {
                src+=s;
            }
            Log.i("aaaa", "onActivityResult: "+src);
            Intent intent = new Intent(this, Main3Activity.class);
            intent.putStringArrayListExtra("aa", path);
            startActivity(intent);
            // 处理你自己的逻辑 ....

        }
    }


    public static String getPinYin(String src) {
        char[] hz = null;
        hz = src.toCharArray();//该方法的作用是返回一个字符数组，该字符数组中存放了当前字符串中的所有字符
        String[] py = new String[hz.length];//该数组用来存储
        //设置汉子拼音输出的格式
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        String pys = ""; //存放拼音字符串
        int len = hz.length;

        try {
            for (int i = 0; i < len; i++) {
                //先判断是否为汉字字符
                if (Character.toString(hz[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    //将汉字的几种全拼都存到py数组中
                    py = PinyinHelper.toHanyuPinyinStringArray(hz[i], format);
                    //取出改汉字全拼的第一种读音，并存放到字符串pys后
                    pys += py[0] + ",";
                } else {
                    //如果不是汉字字符，间接取出字符并连接到 pys 后
                    pys += Character.toString(hz[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pys;
    }

    @OnClick(R.id.tv)
    public void onTvClicked() {
        tv.setText(getPinYin("等我我拗口看到我小搜案件的境外是哦大家洼"));
    }

    @OnClick(R.id.qr)
    public void onQrClicked() { if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
    } else {
        startActivity(new Intent(this, Main2Activity.class));
    }
    }

    @OnClick(R.id.LJ)
    public void onLJClicked() {        startActivity(new Intent(this, GPUActivity.class));
    }

    @OnClick(R.id.multiSelect)
    public void onMultiSelectClicked() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            MultiImageSelector.create(this)
                    .showCamera(false) // 是否显示相机. 默认为显示
                    .count(20) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
//                        .single() // 单选模式
                    .multi() // 多选模式, 默认模式;
                    //   .origin( ) // 默认已选择图片. 只有在选择模式为多选时有效
                    .start(this, 666);

        }
//        String[] strings={"/storage/emulated/0/Pictures/Screenshots/S00513-13323588.png" ,
//            "/storage/emulated/0/Pictures/Screenshots/S00513-13323368.png" ,
//                    "/storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1589346784176.jpg" ,
//                    "/storage/emulated/0/Pictures/Screenshots/S00513-12500992.png" ,
//                    "/storage/emulated/0/Pictures/Screenshots/S00513-12352420.jpg" ,
//                    "/storage/emulated/0/Pictures/Screenshots/S00513-12253544.png" ,
//                    "/storage/emulated/0/Pictures/Screenshots/S00513-12223159.png" ,
//                    "/storage/emulated/0/Pictures/Screenshots/S00513-12221584.png" ,
//                    "/storage/emulated/0/Pictures/Screenshots/S00513-12215847.png" ,
//                    "/storage/emulated/0/Pictures/Screenshots/S00513-12051161.jpg" ,
//                    "/storage/emulated/0/DCIM/pipixia/1589336279309.jpeg" ,
//                    "/storage/emulated/0/DCIM/pipixia/1589336268626.jpeg" ,
//                    "/storage/emulated/0/DCIM/pipixia/1589336262357.jpeg" ,
//                    "/storage/emulated/0/Pictures/Screenshots/S00512-23360650.png",
//                    "/storage/emulated/0/Pictures/Screenshots/Game/S00512-22431418.png",
//                    "/storage/emulated/0/tencent/QQ_Images/-63616a8521bb8e78.jpg" ,
//                    "/storage/emulated/0/Pictures/Screenshots/Game/S00512-22433099.png",
//                    "/storage/emulated/0/Pictures/Screenshots/S00512-20510648.png" ,
//                    "/storage/emulated/0/Pictures/Screenshots/S00512-20242905.jpg" ,
//                    "/storage/emulated/0/Pictures/Screenshots/S00512-20112819.png"
//};
//        ArrayList<String> strings1= new ArrayList<>();
//        for (String s:strings
//             ) {
//            strings1.add(s);
//        }
//        Intent intent = new Intent(this, Main3Activity.class);
//        intent.putStringArrayListExtra("aa",   strings1);
//        startActivity(intent);
    }
}
