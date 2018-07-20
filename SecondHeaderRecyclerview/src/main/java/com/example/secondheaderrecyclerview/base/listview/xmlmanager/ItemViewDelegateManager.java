package com.example.secondheaderrecyclerview.base.listview.xmlmanager;

import android.support.v4.util.SparseArrayCompat;
import android.util.Log;

import com.example.secondheaderrecyclerview.base.listview.viewholder.ViewHolder;


/**
 * itemView的代表管理.
 * <p>
 * Created by zhy on 16/6/22.
 */
public class ItemViewDelegateManager<T> {

    private static final String TAG = "ItemViewDelegateManager";
    /**
     * SparseArrayCompat:
     * 优点节省最高50%缓存;
     * SparseArrayCompat()其实是一个map容器,它使用了一套算法优化了hashMap,可以节省至少50%的缓存.
     * 缺点但是有局限性只针对下面类型.
     * key: Integer; value: object
     */
    SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat();

    public int getItemViewDelegateCount() {
        return delegates.size();
    }

    /*---------两种添加-----------*/
    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate) {

        int viewType = delegates.size();
        if (delegate != null) {
            delegates.put(viewType, delegate);
            viewType++;
        }

        return this;
    }

    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate) {

        if (delegates.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "An ItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemViewDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);

        return this;
    }

    /*---------两种删除-----------*/
    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(int itemType) {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    /*---------数据源和布局相对应满足自定设定的条件的话,拿到key值-----------*/

    /**
     * 大致上是在 adapter 调用 getItemType 的时候，遍历所有 delegate，调用 delegate 的 isForViewType 来
     * 判断是否是自己的类型，如果是的话就停止遍历，返回这个 delegate
     *
     * @param item
     * @param position
     * @return
     */
    public int getItemViewType(T item, int position) {

        int delegatesCount = delegates.size();

        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                Log.i(TAG, "getItemViewType: " + delegates.keyAt(i));
                return delegates.keyAt(i);
            }
        }

        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    /*-------紧接其上,对应之后填充数据.-------*/
    public void convert(ViewHolder holder, T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }


    /*--------------*/
    public int getItemViewLayoutId(int viewType) {
        return delegates.get(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(ItemViewDelegate itemViewDelegate) {
        return delegates.indexOfValue(itemViewDelegate);
    }

    /*---------数据源和布局相对应满足自定设定的条件的话,拿到布局和position-----------*/
    public ItemViewDelegate getItemViewDelegate(T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return delegate;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public int getItemViewLayoutId(T item, int position) {
        return getItemViewDelegate(item, position).getItemViewLayoutId();
    }
}
