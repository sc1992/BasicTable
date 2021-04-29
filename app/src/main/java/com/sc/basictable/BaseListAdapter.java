package com.sc.basictable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * class description
 * 基础适配器
 * @author
 * @date
 */
public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<T> mDataList;
    private int mLayoutId;
    private int mFixX;
    private ArrayList<View> mMoveViewList = new ArrayList<>();

    public BaseListAdapter(Context context,List<T> dataList,int layoutId){
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList;
        mLayoutId = layoutId;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(mLayoutId,parent,false);
        BaseViewHolder holder = new BaseViewHolder(itemView);
        //获取列表右侧可滑动的部分
        LinearLayout moveLayout = holder.getView(R.id.id_move_layout);//由继承的子控件自定义
        setWidths(holder,moveLayout);

        moveLayout.scrollTo(mFixX,0);
        mMoveViewList.add(moveLayout);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        bindData(holder, mDataList.get(position));
    }

    public abstract void bindData(BaseViewHolder holder, T data);

    public abstract void setWidths(BaseViewHolder holder, LinearLayout moveLayout);

    public ArrayList<View> getMoveViewList(){
        return mMoveViewList;
    }

    @Override
    public int getItemCount() {
        return mDataList == null?0:mDataList.size();
    }

    /**
     * 设置列表滚动的位置
     * @param fixX
     */
    public void setFixX(int fixX){
        mFixX=fixX;
    }
}
