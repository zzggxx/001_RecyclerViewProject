package com.example.secondheaderrecyclerview.base.recyclerview.xmlmanager;


import com.example.secondheaderrecyclerview.base.recyclerview.viewholder.ViewHolder;

/**
 * 我们定义一个接口管理每条item的布局
 * <p>
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T> {

    /**
     * 布局文件R.layout.xxxx
     *
     * @return
     */
    public abstract int getItemViewLayoutId();

    /**
     * Called to determine whether this ItemViewDelegate is the responsible for the given data element
     * 是当符合某一条件时就使用这个布局，比如数据 T.getID = 1; 就是说你自己规定一个满足使用此布局的条件，并且该条件通常跟数据源T有关.
     *
     * @param item
     * @param position
     * @return
     */
    public abstract boolean isForViewType(T item, int position);

    /**
     * holder做操作,数据填充
     *
     * @param holder
     * @param t
     * @param position
     */
    public abstract void convert(ViewHolder holder, T t, int position);


}
