package com.example.will.recyclerviewproject.normaluse.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.will.recyclerviewproject.R;
import com.example.will.recyclerviewproject.normaluse.ui.adapter.MyAdapterSecond;
import com.example.will.recyclerviewproject.normaluse.ui.base.adapter.BaseAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyAdapterSecond mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);
        /*---------------------------------------------------------------------------------*/
//        first
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        /*-----------一下两种分割线仅适用于LinearLayoutManager--------------------------------*/
//        first.first default itemDecoration,高度为2px，颜色为灰色
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        /*-------------------------------------------*/
//        first.second custom itemDecoration,可自定义分割线drawable
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.customdecorationdrawable));
        mRecyclerView.addItemDecoration(decoration);
        /*-------------------------------------------*/

        /*---------------------------------------------------------------------------------*/
//        second
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        /*---------------------------------------------------------------------------------*/
//        three
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        /*---------------------------------------------------------------------------------*/


        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mAdapter = new MyAdapter();
        mAdapter = new MyAdapterSecond();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addDatas(generateData());
        setHeader(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                Toast.makeText(MainActivity.this, String.valueOf(data), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.add_header).setOnClickListener(this);
    }

    private ArrayList<String> generateData() {

        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringArrayList.add("数据" + i);
        }

        return stringArrayList;
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.header, view, false);
        mAdapter.addHeaderView(header);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_header:
                setHeader(mRecyclerView);
                break;
            case R.id.delete_header:
                break;
        }
    }
}
