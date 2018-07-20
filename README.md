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
3. PersonCloth是我们重点要为其装饰者添加更多属性或者功能的类,有着很重要的职责,其内部必须要有一个被装饰者的引用.
4. ExpensiveCloth就是具体实现类.
5. 单元测试就是客户的调用.



