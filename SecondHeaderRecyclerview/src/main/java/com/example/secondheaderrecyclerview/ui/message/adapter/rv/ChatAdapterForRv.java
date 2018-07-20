package com.example.secondheaderrecyclerview.ui.message.adapter.rv;

import android.content.Context;

import com.example.secondheaderrecyclerview.base.recyclerview.adapter.MultiItemTypeAdapter;
import com.example.secondheaderrecyclerview.ui.message.bean.ChatMessage;

import java.util.List;

/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapterForRv extends MultiItemTypeAdapter<ChatMessage>
{
    public ChatAdapterForRv(Context context, List<ChatMessage> datas)
    {
        super(context, datas);

        addItemViewDelegate(new MsgSendItemDelagate());
        addItemViewDelegate(new MsgComingItemDelagate());
    }
}
