package com.example.secondheaderrecyclerview.ui.message.adapter.lv;

import android.content.Context;

import com.example.secondheaderrecyclerview.base.listview.adapter.MultiItemTypeAdapter;
import com.example.secondheaderrecyclerview.ui.message.bean.ChatMessage;

import java.util.List;

/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapter extends MultiItemTypeAdapter<ChatMessage> {
    public ChatAdapter(Context context, List<ChatMessage> datas) {
        super(context, datas);

        /**
         * 只是两种类型,添加了两次
         */
        addItemViewDelegate(new MsgSendItemDelagate(context));
        addItemViewDelegate(new MsgComingItemDelagate(context));
    }

}
