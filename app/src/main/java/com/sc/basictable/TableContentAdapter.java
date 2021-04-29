package com.sc.basictable;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * class description
 * 定制适配器类（根据需求，id根据布局）
 * @author
 * @date
 */
public class TableContentAdapter extends BaseListAdapter<StockCodeInfo>{

    private BaseViewHolder.onItemClickListener mItemClickListener;
    private int width[];

    public TableContentAdapter(Context context, List<StockCodeInfo> dataList, int width[],int layoutId , BaseViewHolder.onItemClickListener listener) {
        super(context, dataList, layoutId);
        this.width = width;
        mItemClickListener = listener;
    }

    @Override
    public void setWidths(BaseViewHolder holder, LinearLayout moveLayout) {
        //要给每一列都重新设置宽度
        TextView tvName = holder.getView(R.id.id_name);
        tvName.setWidth(width[0]);
        TextView lastPrice = holder.getView(R.id.id_tv_price_last);
        lastPrice.setWidth(width[1]);
        TextView tvRise = holder.getView(R.id.id_tv_rise_rate);
        tvRise.setWidth(width[2]);
        TextView tvVol = holder.getView(R.id.id_tv_vol);
        tvVol.setWidth(width[3]);
        TextView tvClose = holder.getView(R.id.id_tv_close);
        tvClose.setWidth(width[4]);
        TextView tvOpen = holder.getView(R.id.id_tv_open);
        tvOpen.setWidth(width[5]);
        TextView tvBid = holder.getView(R.id.id_tv_up);
        tvBid.setWidth(width[6]);
        TextView tvAsk = holder.getView(R.id.id_tv_down);
        tvAsk.setWidth(width[7]);
        TextView tvPercent = holder.getView(R.id.id_tv_stockCode);
        tvPercent.setWidth(width[8]);

        ViewGroup.LayoutParams lp;
        lp = moveLayout.getLayoutParams();//宽度设置以后，需要对滑动布局重新设定宽度
        lp.width = getTotalWidth();
        moveLayout.setLayoutParams(lp);
    }

    /**
     * 获取到设置的总宽度
     * @return
     */
    private int getTotalWidth(){
        int moveWidth = 0;
        for(int i=0;i<width.length;i++){
            moveWidth = moveWidth + width[i];
        }
        return moveWidth;
    }

    @Override
    public void bindData(BaseViewHolder holder, StockCodeInfo data) {

        holder.setText(R.id.id_name, data.getStockName())
                .setText(R.id.id_tv_price_last, data.getLastPrice())
                .setText(R.id.id_tv_rise_rate, data.getZfRate())
                .setText(R.id.id_tv_vol, data.getZfValue())
                .setText(R.id.id_tv_close, data.getClosePrice())
                .setText(R.id.id_tv_open, data.getOpenPrice())
                .setText(R.id.id_tv_up, data.getUpPrice())
                .setText(R.id.id_tv_down, data.getDownPrice())
                .setText(R.id.id_tv_stockCode, data.getStockCode())
                .setOnItemClickListener(mItemClickListener);
    }
}
