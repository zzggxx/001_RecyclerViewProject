package com.example.secondheaderrecyclerview.base.listview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.secondheaderrecyclerview.base.listview.viewholder.ViewHolder;
import com.example.secondheaderrecyclerview.base.listview.xmlmanager.ItemViewDelegate;
import com.example.secondheaderrecyclerview.base.listview.xmlmanager.ItemViewDelegateManager;

import java.util.List;

public class MultiItemTypeAdapter<T> extends BaseAdapter {

    private static final String TAG = "MultiItemTypeAdapter";
    protected Context mContext;
    protected List<T> mDatas;

    private ItemViewDelegateManager mItemViewDelegateManager;


    /**
     * 多种类型是在这里添加
     *
     * @param context
     * @param datas
     */
    public MultiItemTypeAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    private boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    @Override
    public int getViewTypeCount() {
        if (useItemViewDelegateManager()) {
            return mItemViewDelegateManager.getItemViewDelegateCount();
        }
        return super.getViewTypeCount();
    }

    /*----------------原本方法---------------------*/
    @Override
    public int getItemViewType(int position) {
        if (useItemViewDelegateManager()) {
            Log.i(TAG, "getItemViewType: " + position);
            return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(mDatas.get(position), position);
        int layoutId = itemViewDelegate.getItemViewLayoutId();

        ViewHolder viewHolder = null;
        if (convertView == null) {

            View itemView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
            viewHolder = new ViewHolder(mContext, itemView, parent, position);
            viewHolder.mLayoutId = layoutId;

            onViewHolderCreated(viewHolder, viewHolder.getConvertView());

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mPosition = position;

        }

        convert(viewHolder, getItem(position), position);

        return viewHolder.getConvertView();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    /*-------------------------------------*/

    //    填充数据
    protected void convert(ViewHolder viewHolder, T item, int position) {
        mItemViewDelegateManager.convert(viewHolder, item, position);
    }

    //    布局
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
    }


}
