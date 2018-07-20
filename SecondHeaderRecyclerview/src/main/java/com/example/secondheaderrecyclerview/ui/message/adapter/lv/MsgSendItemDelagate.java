package com.example.secondheaderrecyclerview.ui.message.adapter.lv;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.secondheaderrecyclerview.R;
import com.example.secondheaderrecyclerview.base.listview.viewholder.ViewHolder;
import com.example.secondheaderrecyclerview.base.listview.xmlmanager.ItemViewDelegate;
import com.example.secondheaderrecyclerview.ui.message.bean.ChatMessage;

/**
 * Created by zhy on 16/6/22.
 */
public class MsgSendItemDelagate implements ItemViewDelegate<ChatMessage> {

    private Context mContext;

    public MsgSendItemDelagate(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.main_chat_send_msg;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position) {
        return !item.isComMeg();
    }

    @Override
    public void convert(ViewHolder holder, final ChatMessage chatMessage, final int position) {
        holder.setText(R.id.chat_send_content, chatMessage.getContent());
        holder.setText(R.id.chat_send_name, chatMessage.getName());
        holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon());

        holder.getView(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, chatMessage.getContent() + "___" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
