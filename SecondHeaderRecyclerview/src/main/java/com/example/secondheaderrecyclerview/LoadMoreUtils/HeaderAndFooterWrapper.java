package com.example.secondheaderrecyclerview.LoadMoreUtils;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondheaderrecyclerview.base.recyclerview.viewholder.ViewHolder;

/**
 * Created by will on 2018/7/9.
 */

public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 100000;

    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    /**
     * key-类型(100000开始增加);value-View布局.
     */
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    /**
     * 现加的头和尾使用现在的ViewHolder.
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mHeaderViews.get(viewType) != null) {

            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            return holder;

        } else if (mFootViews.get(viewType) != null) {

            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));
            return holder;

        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    /**
     * 放入的时候创建了每一个的itemType.
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (isHeaderViewPos(position)) {

            return mHeaderViews.keyAt(position);

        } else if (isFooterViewPos(position)) {

            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());

        }

        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    /**
     * 很重要,使用的是被装饰者 mInnerAdapter.
     *
     * @return
     */
    public int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }


    /**
     * 绑定数据,后边的拓展.这里仅仅只是为了展示才直接return的,尤其是联网请求的时候数据要做好封装性.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        此处可以书写自定义的数据绑定.
        if (isHeaderViewPos(position)) {
            return;
        }
        if (isFooterViewPos(position)) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }
}
