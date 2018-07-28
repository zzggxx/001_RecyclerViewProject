# 一.对RecyclerView的知识点全面讲解

引入依赖包：有时候v7支持包中是没有的,应该进行引入并且其版本和编译环境的相同(注意是编译环境相同,eg:compileSdkVersion 27,只需要将地下的23变成27即可).

    compile 'com.android.support:recyclerview-v7:23+'

## 二.增加头部的两种方式

### 2.1 Adapter方式增加头部(FirstHeaderRecyclerView)

知识点:

1. BaseAdapter的抽取和抽象,留下了变化的item布局和绑定数据;
2. 分割线设置方式(只是LinearLayoutManager其他的暂未研究透)
3. LinearLayoutManager,GridLayoutManager,StaggeredGridLayoutManager三种方式,正反布局都是可以的.注意瀑
布流适应了item的自身的.
4. 头部占一个不同的单元格时不同布局对应的设计方法.

### 2.2 使用装饰者模式(FirstHeaderRecyclerView)

知识点:

1. 可以随意的增加头部和尾部,并且里边的Adapter可以和没有头部的RecyclerView共用一个,在项目的后期其拓展性的方
面是有很大的帮助的
2. 这里的装饰就是将Adapter层层的包装.为每一个headerView或者footerView都添加了一个itemType.
3. 关于多布局的管理者模式的讲解

思考:这里的使用方法是装饰者模式,是使用的更加优雅呢还是根本就不是呢?

## 三.多布局管理者模式的讲解

[hongyangAndroid/baseAdapter传送门](https://github.com/hongyangAndroid/baseAdapter)

1. 有两种都叫做MultiItemTypeAdapter,一个是继承自baseAdapter一个是RecyclerView.Adapter
2. ItemViewDelegate定义布局,数据匹配,填充数据.三种方法,而ItemViewDelegateManager是对其布局的方法引用,
MultiItemTypeAdapter引用ItemViewDelegateManager管理布局和类型
3. 注意MainActivity中ListView的使用的是一种布局,而MultiItemListViewActivity使用的多种布局.
4. 多种布局中布局和填充数据的方法判断是一个也没有少只是方法增多了变得更加简洁了而已.
5. ListView 和 RecyclerView 的布局管理者都是一样的,只是继承的Adapter不一样罢了
6. ItemViewDelegate 就是将布局,类型区分,绑定数据抽出了Adapter之外,有利于Item的复用,eg:
RecyclerView A(有item类型1234) 和 B(有item类型23456) 那么他们大部分是一样的所以把他们抽出来起到了
<font color=#ff0000>**复用item**</font>的效果.

## 四.装饰者模式讲解

1. Person类是被装饰者的原始对象,可以是一个抽象或者一个接口.
2. 而Boy就是我们的需要的重点对象,被装饰者.
3. PersonCloth是我们重点要为其装饰者添加更多属性或者功能的类,有着很重要的职责,其内部必须要有一个被装饰者的引用.如果装饰逻辑单一可以直接省略该类,直接的使用ExpensiveCloth并在其中引用对象.
4. ExpensiveCloth就是具体实现类.
5. 单元测试就是客户的调用.

![UML](https://raw.githubusercontent.com/zzggxx/Picture/master/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F/decorator_pattern.jpg)

这里的抽离模式是更加的充分,目前水平有限先贴出地址以后学习,
[传送门](https://github.com/drakeet/MultiType)

## 五.ChangeUISpecialMethods,UI新的更新方法.

* notifyItemChanged(int position) 更新列表position位置上的数据可以调用,更新的时候还是刷新的控件级别吧,因为若是有图片什么的会造成图片闪烁的问题.
* notifyItemInserted(int position) 列表position位置添加一条数据时可以调用，伴有动画效果
* notifyItemRemoved(int position) 列表position位置移除一条数据时调用，伴有动画效果,但是这个删除是有问题的,应该先更新数据源,然后移除并且更新后边的数据,参见demo即可.并且应该注意若是走网络删除,操作前后应该锁定操作按钮.
* notifyItemMoved(int fromPosition, int toPosition) 列表fromPosition位置的数据移到toPosition位置时调用，伴有动画效果
* notifyItemRangeChanged(int positionStart, int itemCount) 列表从positionStart位置到itemCount数量的列表项进行数据刷新
* notifyItemRangeInserted(int positionStart, int itemCount) 列表从positionStart位置到itemCount数量的列表项批量添加数据时调用，伴有动画效果
* notifyItemRangeRemoved(int positionStart, int itemCount) 列表从positionStart位置到itemCount数量的列表项批量删除数据时调用，伴有动画效果



