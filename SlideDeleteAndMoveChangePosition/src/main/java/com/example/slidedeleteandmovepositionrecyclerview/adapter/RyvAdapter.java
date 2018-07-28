package com.example.slidedeleteandmovepositionrecyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.slidedeleteandmovepositionrecyclerview.R;
import com.example.slidedeleteandmovepositionrecyclerview.base.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class RyvAdapter extends RecyclerView.Adapter<RyvAdapter.MyViewHolder> implements ItemTouchHelperAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<String> mTitle;

    public RyvAdapter(Context context, ArrayList<String> title) {
        mContext = context;
        mTitle = title;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.item_layout1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String s = mTitle.get(position);

        holder.mTextView.setText(s);
    }

    @Override
    public int getItemCount() {
        return mTitle == null ? 0 : mTitle.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mTitle, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        mTitle.remove(position);
        notifyItemRemoved(position);
//        别忘记后边数据变更
        notifyItemRangeChanged(position, getItemCount());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView, mTvText;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.item);
            mTvText = itemView.findViewById(R.id.tv_text);
        }
    }


}
