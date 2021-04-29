package com.sc.basictable.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sc.basictable.BaseListAdapter;
import com.sc.basictable.R;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * class description
 * 左侧固定，右侧可滑动的列表类
 * @author sc
 * @date 2021-04-29
 */
public class TableGroup extends LinearLayout {
    private int mHeadTextSize,mContentTextSize;

    private int mHeadTextColor,mContentTextColor;

    private String mHeadList[];
    //标题的宽度集合
    private int[] mHeadTextWidthList;
    private int mHeadHeight;
    private LinearLayout mRightTitleLayout;

    //展示数据时使用的RecycleView
    private RecyclerView mRecyclerView;
    private BaseListAdapter mAdapter;

    public TableGroup(Context context) {
        super(context);
        initData();
    }

    public TableGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public TableGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData(){
        setOrientation(VERTICAL);
        mHeadTextSize = getResources().getDimensionPixelSize(R.dimen.font18);
        mContentTextSize = getResources().getDimensionPixelSize(R.dimen.font16);
        mHeadHeight = getResources().getDimensionPixelSize(R.dimen.dip40);

        mHeadTextColor = getResources().getColor(R.color.black);
        mContentTextColor = getResources().getColor(R.color.gray);
    }

    /**
     * 创建头部布局
     * @return
     */
    private View createHeadLayout(){
        LinearLayout headLayout = new LinearLayout(getContext());
        headLayout.setGravity(Gravity.CENTER);

        addHeaderTextView(mHeadList[0],mHeadTextWidthList[0],headLayout);

        mRightTitleLayout = new LinearLayout(getContext());
        for(int i=1;i<mHeadList.length;i++){
            addHeaderTextView(mHeadList[i],mHeadTextWidthList[i],mRightTitleLayout);
        }
        headLayout.addView(mRightTitleLayout);
        return headLayout;
    }

    //需要滑动的View集合
    private ArrayList<View> mMoveViewList = new ArrayList();
    /**
     * 创建数据展示布局
     * @return
     */
    private View createContentLayout(){
        RelativeLayout linearLayout = new RelativeLayout(getContext());
        mRecyclerView = new RecyclerView(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        if(mAdapter != null){
            if(mAdapter instanceof BaseListAdapter) {
                mRecyclerView.setAdapter(mAdapter);
                mMoveViewList = mAdapter.getMoveViewList();
            }
        }

        linearLayout.addView(mRecyclerView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));

        return linearLayout;
    }

    private void addHeaderTextView(String name,int width,LinearLayout parentView){
        TextView textView = new TextView(getContext());
        textView.setText(name);
        textView.setGravity(Gravity.CENTER);
        parentView.addView(textView,width,mHeadHeight);
    }

    //手指按下时的位置
    private float mStartX = 0;
    //滑动时和按下时的差值
    private int mMoveOffsetX = 0;
    //最大可滑动差值
    private int mFixX = 0;
    //触发拦截手势的最小值
    private int mTriggerMoveDis = 30;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                break;
                case MotionEvent.ACTION_MOVE:
                    int offsetX = (int) Math.abs(ev.getX() - mStartX);
                    if(offsetX > mTriggerMoveDis){//水平移动大于30触发拦截
                        return true;
                    }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                int offSetX = (int) Math.abs(event.getX() - mStartX);
                if(offSetX > 30){
                    mMoveOffsetX = (int) (mStartX - event.getX() + mFixX);
                    if(0 > mMoveOffsetX){//如果滑到最左端，则不滑动
                        mMoveOffsetX = 0;
                    }else{
                        if((mRightTitleLayout.getWidth() + mMoveOffsetX)> rightTitleTotalWidth()){
                            mMoveOffsetX = rightTitleTotalWidth() - mRightTitleLayout.getWidth();
                        }
                    }
                    //跟随手指向右滚动
                    mRightTitleLayout.scrollTo(mMoveOffsetX, 0);
                    if (null != mMoveViewList) {
                        for (int i = 0; i < mMoveViewList.size(); i++) {
                            //使每个item随着手指向右滚动
                            mMoveViewList.get(i).scrollTo(mMoveOffsetX, 0);
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mFixX = mMoveOffsetX; //设置最大水平平移的宽度
                mAdapter.setFixX(mFixX);
                break;
        }

        return super.onTouchEvent(event);
    }

    //右边可滑动的总宽度
    private int mRightTotalWidth = 0;
    /**
     * 右边可滑动的总宽度
     * @return
     */
    private int rightTitleTotalWidth() {
        if (0 == mRightTotalWidth) {
            for (int i = 1; i < mHeadList.length; i++) {
                mRightTotalWidth = mRightTotalWidth + mHeadTextWidthList[i];
            }
        }
        return mRightTotalWidth;
    }

    /**
     * 设置表头，和表头每一栏的宽度
     * @param headers
     * @param headWidth
     */
    public void setHeaders(String headers[],int headWidth[]){
        mHeadList = headers;
        mHeadTextWidthList = headWidth;
    }

    /**
     * 添加视图
     * @param adapter
     */
    public void setAdapter(BaseListAdapter adapter){
        mAdapter = adapter;
        addView(createHeadLayout());
        addView(createContentLayout());
    }

}
