package com.example.will.recyclerviewproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.will.recyclerviewproject.alltype.AllTypeRecyclerView;
import com.example.will.recyclerviewproject.normaluse.ui.MainActivity;

public class StartUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        findViewById(R.id.narmal_use).setOnClickListener(this);
        findViewById(R.id.all_type).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.narmal_use:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.all_type:
                startActivity(new Intent(this, AllTypeRecyclerView.class));
                break;
        }
    }
}
