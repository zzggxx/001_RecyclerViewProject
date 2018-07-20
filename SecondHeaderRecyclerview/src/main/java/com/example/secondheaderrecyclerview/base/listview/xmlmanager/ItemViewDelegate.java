package com.example.secondheaderrecyclerview.base.listview.xmlmanager;


import com.example.secondheaderrecyclerview.base.listview.viewholder.ViewHolder;


/**
 * 在不同的 Activity 中，长得一样、逻辑也差不多的 ListView、RecyclerView 可以使用同一个 Adapter 就完成 Item 的展示。
 * 但是考虑到 ItemType 的话，事情就会复杂些许。例如：我有一个拥有类型1和类型2的 ListView 取名为 A，我有一个拥有类型2和
 * 类型3的 ListView 取名为 B，如果按照上面的做法，那么我只能写两个 Adapter 。如果把情况考虑复杂一点，A 对应123，
 * B 对应234，那么 A 和 B 他们有一大半的 Item 类型是一致的，这意味着我们的重复代码会很多。再考虑极端一些呢？……
 * <p>
 * 可是，A 和 B 的不同导致他们几乎不得不使用不同的 Adapter，那我们应该怎么办呢？
 * <p>
 * 我个人有一个观点：""""对 Adapter 的复用实质上是为了对 Item 的复用""""，这里的 Item 包括布局、数据绑定、事件监听等。
 * 既然 Adapter 一定是不同的，为了实现 Item 的复用，我们是不是应该考虑把布局、数据绑定、事件监听等处理从 Adapter 中剥离出来？
 * <p>
 * 于是 Delegate 类的价值就产生出来了
 */

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
     * holder做操作,数据填充和点击事件等
     *
     * @param holder
     * @param t
     * @param position
     */
    public abstract void convert(ViewHolder holder, T t, int position);


}
