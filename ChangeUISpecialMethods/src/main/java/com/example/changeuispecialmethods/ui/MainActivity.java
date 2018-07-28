package com.example.changeuispecialmethods.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.changeuispecialmethods.R;
import com.example.changeuispecialmethods.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    //item 显示所需
    private String[] title = {"JAVA", "C", "C++", "C#", "PYTHON", "PHP", ".NET", "JAVASCRIPT",
            "RUBY", "PERL", "VB", "OC", "SWIFT"};

    private ArrayList<String> mTitle = new ArrayList<>();
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //去除Toolbar标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //设置菜单点击监听
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        mRecyclerView = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        Collections.addAll(mTitle, title);
        //为RecyclerView添加默认动画效果，测试不写也可以
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRecyclerViewAdapter = new RecyclerViewAdapter(this, mTitle));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private int i;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.menu_change:

                    //添加模拟数据到第一项
                    mTitle.set(0, "哎呦我去~~~~~" + ++i);
                    mRecyclerViewAdapter.notifyItemChanged(0);
                    break;

                case R.id.menu_add:

                    //添加模拟数据到第一项
                    mTitle.add(0, "www.lijizhou.com");
                    //RecyclerView列表进行UI数据更新
                    mRecyclerViewAdapter.notifyItemInserted(0);
                    //如果在第一项添加模拟数据需要调用 scrollToPosition（0）把列表移动到顶端（可选）
                    mRecyclerView.scrollToPosition(0);
                    break;

                case R.id.menu_del:
                    //删除模拟数据第一项
                    mTitle.remove(0);
                    //RecyclerView 列表进行UI数据更新
                    mRecyclerViewAdapter.notifyItemRemoved(0);
                    //                    删除会错乱所以应该是同步数据
                    mRecyclerViewAdapter.notifyItemRangeChanged(0, mRecyclerViewAdapter.getItemCount());
                    break;

                case R.id.menu_move:
                    //列表中第二项移到第三项 进行UI数据更新
                    mRecyclerViewAdapter.notifyItemMoved(1, 2);
                    break;

                case R.id.menu_addmore:
                    //模拟数据批量添加4条数据
                    mTitle.add(0, "test");
                    mTitle.add(0, "test1");
                    mTitle.add(0, "test2");
                    mTitle.add(0, "test3");
                    //RecyclerView列表进行批量UI数据更新
                    mRecyclerViewAdapter.notifyItemRangeInserted(0, 4);
                    // scrollToPosition（0）作用是把列表移动到顶端
                    mRecyclerView.scrollToPosition(0);
                    break;

            }

            return true;
        }
    };

}
