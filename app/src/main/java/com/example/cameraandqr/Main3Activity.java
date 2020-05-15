package com.example.cameraandqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class Main3Activity extends AppCompatActivity {
RecyclerView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        view=findViewById(R.id.myRV);
        Intent intent=getIntent();
        List<String> a=intent.getStringArrayListExtra("aa");
        MyAdapter adapter=new MyAdapter();
        adapter.setConfig(this,a);
        view.setAdapter(adapter);
        view.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
    }
}
