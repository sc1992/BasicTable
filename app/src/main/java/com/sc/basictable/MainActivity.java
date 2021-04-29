package com.sc.basictable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.sc.basictable.Functions.BaseFunction;
import com.sc.basictable.weight.TableGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String mHeaders[];
    private int mMinEachWidth;
    private int mEachWidth[];
    private ArrayList<StockCodeInfo> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableGroup tableGroup = (TableGroup) findViewById(R.id.table);
        mDataList = new ArrayList<>();
        for(int i=0;i<100;i++) {
            StockCodeInfo codeInfo = new StockCodeInfo();
            codeInfo.setStockCode("601519大智慧大智慧大智慧大智慧大智慧大智慧");
            codeInfo.setStockName("大智慧");
            codeInfo.setClosePrice("99.99");
            codeInfo.setDownPrice("88.88");
            codeInfo.setLastPrice("99");
            codeInfo.setOpenPrice("88");
            codeInfo.setUpPrice("100");
            codeInfo.setZfRate("100%");
            codeInfo.setZfValue("10");
            mDataList.add(codeInfo);
        }
        mHeaders = getResources().getStringArray(R.array.right_title_name);
        calculateEachWidth();
        tableGroup.setHeaders(mHeaders,mEachWidth);

        TableContentAdapter adapter = new TableContentAdapter(this, mDataList,mEachWidth, R.layout.item_layout, new BaseViewHolder.onItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Toast.makeText(MainActivity.this, "position--->"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(int position) {

            }
        });
        tableGroup.setAdapter(adapter);
    }

    /**
     * 计算每列的宽度，以及总宽度
     */
    private void calculateEachWidth(){
        if(mMinEachWidth == 0){//最小宽度
            mMinEachWidth =  getResources().getDimensionPixelSize(R.dimen.dip50);;
        }

        if(mHeaders != null && mHeaders.length > 0){
            mEachWidth = new int[mHeaders.length];
            for(int i = 0;i < mHeaders.length;i++){
                int width = BaseFunction.stringWidthWithSize(mHeaders[i],getResources().getDimensionPixelSize(R.dimen.font18));
                width = Math.max(width,mMinEachWidth);
                mEachWidth[i] = width;
            }
        }

        if(mDataList != null && mDataList.size() > 0 && mEachWidth != null && mEachWidth.length > 0){
            for(int i=0;i<mHeaders.length;i++){
                for(int j=0;j<mDataList.size();j++) {
                    int width = BaseFunction.stringWidthWithSize(mDataList.get(j).getValue(i), getResources().getDimensionPixelSize(R.dimen.font16));
                    mEachWidth[i] = Math.max(width, mEachWidth[i]);
                }
            }
        }
    }
}