package com.example.secondheaderrecyclerview.base.listview.adapter;

import android.content.Context;

import com.example.secondheaderrecyclerview.base.listview.viewholder.ViewHolder;
import com.example.secondheaderrecyclerview.base.listview.xmlmanager.ItemViewDelegate;

import java.util.List;

public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    public CommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);

        /**
         * 只是添加了一种类型
         */
        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder viewHolder, T item, int position);

}
