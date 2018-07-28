package com.example.slidedeleteandmovepositionrecyclerview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.slidedeleteandmovepositionrecyclerview.R;
import com.example.slidedeleteandmovepositionrecyclerview.adapter.RyvAdapter;
import com.example.slidedeleteandmovepositionrecyclerview.base.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRyv;
    private ArrayList<String> mStringArrayList = new ArrayList<>();
    private RyvAdapter mRyvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRyv = findViewById(R.id.ryv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRyv.setLayoutManager(layoutManager);

        addData();
        mRyvAdapter = new RyvAdapter(this, mStringArrayList);

        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mRyvAdapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(mRyv);

        mRyv.setAdapter(mRyvAdapter);

    }

    private void addData() {
        for (int i = 0; i < 100; i++) {
            mStringArrayList.add("a" + i);
        }
    }
}
