package com.example.will.recyclerviewproject.alltype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.will.recyclerviewproject.R;

public class AllTypeRecyclerView extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_type_recycler_view);

        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        findViewById(R.id.bt5).setOnClickListener(this);
        findViewById(R.id.bt6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, WidgetActivity.class);
        switch (v.getId()) {
            case R.id.bt1:
                intent.putExtra("orientation", WidgetActivity.LIST_V);
                break;
            case R.id.bt2:
                intent.putExtra("orientation", WidgetActivity.LIST_H);
                break;
            case R.id.bt3:
                intent.putExtra("orientation", WidgetActivity.GRID_V);
                break;
            case R.id.bt4:
                intent.putExtra("orientation", WidgetActivity.GRID_H);
                break;
            case R.id.bt5:
                intent.putExtra("orientation", WidgetActivity.STAGGERED_GRID_V);
                break;
            case R.id.bt6:
                intent.putExtra("orientation", WidgetActivity.STAGGERED_GRID_H);
                break;
        }

        startActivity(intent);
    }
}
