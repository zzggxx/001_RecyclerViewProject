package com.example.will.recyclerviewproject.normaluse.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.will.recyclerviewproject.R;
import com.example.will.recyclerviewproject.normaluse.ui.base.adapter.BaseAdapter;

public class MyAdapterSecond extends BaseAdapter<String> {
    @Override
    public RecyclerView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyAdapterSecondHolder(layout);
    }

    @Override
    public void onBindData(RecyclerView.ViewHolder viewHolder, int RealPosition, String data) {
        if(viewHolder instanceof MyAdapterSecondHolder) {
            ((MyAdapterSecondHolder) viewHolder).tv.setText(data);
        }
    }


    private class MyAdapterSecondHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public MyAdapterSecondHolder(View layout) {
            super(layout);
            tv = layout.findViewById(R.id.text);
        }
    }
}
