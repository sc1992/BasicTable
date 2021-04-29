package com.sc.basictable;

import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * class description
 *
 * @author
 * @date
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

    private SparseArray<View> viewSparseArray;
    private onItemClickListener mOnItemClickListener;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnLongClickListener(this);
        itemView.setOnClickListener(this);
        viewSparseArray = new SparseArray<>();
    }
    /**
     * 根据 ID 来获取 View
     *
     * @param viewId viewID
     * @param <T>    泛型
     * @return 将结果强转为 View 或 View 的子类型
     */
    public <T extends View> T getView(int viewId) {
        // 先从缓存中找，找打的话则直接返回
        // 如果找不到则 findViewById ，再把结果存入缓存中
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }


    public interface onItemClickListener {

        void onItemClickListener(int position);

        void onItemLongClickListener(int position);

    }

    public void setOnItemClickListener(onItemClickListener listener){
        mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClickListener(getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemLongClickListener(getAdapterPosition());
        }
        return false;
    }
}
