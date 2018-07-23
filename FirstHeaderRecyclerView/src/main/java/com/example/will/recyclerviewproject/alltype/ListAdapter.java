package com.example.will.recyclerviewproject.alltype;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.will.recyclerviewproject.R;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    int icons[] = {R.drawable.vector_icon_cloud, R.drawable.vector_icon_movie,
            R.drawable.vector_icon_laptop, R.drawable.vector_icon_loop, R.drawable.vector_icon_menu,
            R.drawable.vector_icon_mood, R.drawable.vector_icon_palette, R.drawable.vector_icon_search,
            R.drawable.vector_icon_time, R.drawable.vector_icon_work};
    String names[] = {"Cloud", "Movie", "Laptop", "Loop", "Menu", "Mood", "Palette", "Search", "Time", "Work"};
    Context mContext;
    boolean flag;
    private int position;

    public ListAdapter(Context context, boolean flag) {
        this.mContext = context;
        this.flag = flag;
    }


    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(flag ? R.layout.list_item : R.layout.list_item_h, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        holder.setData(position);
        holder.initClick(position);
    }

    @Override
    public int getItemCount() {
        return 1000;
    }

    class ListHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        TextView subName;
        RelativeLayout rl;

        public ListHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            name = (TextView) itemView.findViewById(R.id.name);
            subName = (TextView) itemView.findViewById(R.id.subname);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl);
        }

        public void setData(int position) {
            icon.setImageDrawable(mContext.getResources().getDrawable(icons[position % 10]));
            name.setText(names[position % 10]);
            subName.setText("This is position " + position);
        }

        public void initClick(final int position) {
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "icon被点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "name被点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            subName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "subname被点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItenClickListener != null) {
                        Toast.makeText(mContext, "rl被点击了,我要传给适配器了啊!" + position, Toast.LENGTH_SHORT).show();
                        mOnItenClickListener.onRecyclerViewItenClick(position);
                    }
                }
            });
        }
    }

    public interface OnItenClickListener {
        void onRecyclerViewItenClick(/*View view, */int position);
    }

    public OnItenClickListener mOnItenClickListener;

    public void setOnItenClickListener(OnItenClickListener mOnItenClickListener) {
        this.mOnItenClickListener = mOnItenClickListener;
    }
}
