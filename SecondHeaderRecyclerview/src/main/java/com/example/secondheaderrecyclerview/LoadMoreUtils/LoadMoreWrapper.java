package com.example.secondheaderrecyclerview.LoadMoreUtils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondheaderrecyclerview.base.recyclerview.viewholder.ViewHolder;


/**
 * Created by zhy on 16/6/23.
 */
public class LoadMoreWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;

    public int loadMoreStatus = 0;

    private HeaderAndFooterWrapper mInnerAdapter;
    private View mLoadMoreView;
    private int mLoadMoreLayoutId;
    private int mPageSize;

    public LoadMoreWrapper(HeaderAndFooterWrapper adapter) {
        mInnerAdapter = adapter;
    }

    private boolean hasLoadMore() {
        return mLoadMoreView != null || mLoadMoreLayoutId != 0;
    }


    /**
     * 一般出问题就是loadMore的时候出的问题.(尤其注意mInnerAdapter的问题.)
     *
     * @param position
     * @return
     */
    private boolean isShowLoadMore(int position) {

        return hasLoadMore() && position >= mInnerAdapter.getItemCount() &&
                position != 0 && mInnerAdapter.getRealItemCount() > 0 &&
                mInnerAdapter.getRealItemCount() >= mPageSize;
    }

    @Override
    public int getItemViewType(int position) {

        if (isShowLoadMore(position)) {
            return ITEM_TYPE_LOAD_MORE;
        }

        return mInnerAdapter.getItemViewType(position);
    }

    /**
     * 最后一种类型
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_LOAD_MORE) {
            ViewHolder holder;
            if (mLoadMoreView != null) {
                holder = ViewHolder.createViewHolder(parent.getContext(), mLoadMoreView);
            } else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
            }
            return holder;
        }

        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    /**
     * 注意此处的绑定请求更多数据.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (isShowLoadMore(position)) {
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener.onLoadMoreRequested();
            }
            return;
        }

        mInnerAdapter.onBindViewHolder(holder, position);
    }

    /**
     * 适配不同的 LayoutManager
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isShowLoadMore(position)) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);

        if (isShowLoadMore(holder.getLayoutPosition())) {
            setFullSpan(holder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

            p.setFullSpan(true);
        }
    }

    /**
     * 是否加载到最后一页,此时要么是改变loadMore的布局,要么是取消LoadMore的显示.通过item的数量实现.
     * @return
     */
    @Override
    public int getItemCount() {
        if (loadMoreStatus != -1) {
            return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
        } else {
            return mInnerAdapter.getItemCount();
        }
    }

    public void setLoadMoreViewStatus(int status) {
        loadMoreStatus = status;
    }

    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
    }


    public interface OnLoadMoreListener {
        void onLoadMoreRequested();
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(View loadMoreView) {
        mLoadMoreView = loadMoreView;
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(int layoutId) {
        mLoadMoreLayoutId = layoutId;
        return this;
    }
}
