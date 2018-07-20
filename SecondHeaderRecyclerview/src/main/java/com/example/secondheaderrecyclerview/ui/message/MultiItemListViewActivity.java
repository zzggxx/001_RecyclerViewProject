package com.example.secondheaderrecyclerview.ui.message;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.secondheaderrecyclerview.R;
import com.example.secondheaderrecyclerview.ui.message.adapter.lv.ChatAdapter;
import com.example.secondheaderrecyclerview.ui.message.bean.ChatMessage;

public class MultiItemListViewActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mListView = findViewById(R.id.id_listview_list);
        mListView.setDivider(null);
        mListView.setAdapter(new ChatAdapter(this, ChatMessage.MOCK_DATAS));

    }

}
