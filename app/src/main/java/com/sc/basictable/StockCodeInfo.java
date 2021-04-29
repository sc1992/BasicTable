package com.sc.basictable;

/**
 * class description
 * 股票model类
 * @author sc
 * @date 2021-04-29
 */
public class StockCodeInfo {
    private String stockCode;
    private String stockName;
    private String lastPrice;
    private String openPrice;
    private String closePrice;
    private String upPrice;
    private String downPrice;
    private String zfValue;

    public String getValue(int Position){
        String result = "";
        switch (Position){
            case 0:
                result = stockName;
                break;
            case 1:
                result = lastPrice;
                break;
            case 2:
                result = zfRate;
                break;
            case 3:
                result = zfValue;
                break;
            case 4:
                result = closePrice;
                break;
            case 5:
                result = openPrice;
                break;
            case 6:
                result = upPrice;
                break;
            case 7:
                result = downPrice;
                break;
            case 8:
                result = stockCode;
                break;
        }
        return result;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice;
    }

    public String getUpPrice() {
        return upPrice;
    }

    public void setUpPrice(String upPrice) {
        this.upPrice = upPrice;
    }

    public String getDownPrice() {
        return downPrice;
    }

    public void setDownPrice(String downPrice) {
        this.downPrice = downPrice;
    }

    public String getZfValue() {
        return zfValue;
    }

    public void setZfValue(String zfValue) {
        this.zfValue = zfValue;
    }

    public String getZfRate() {
        return zfRate;
    }

    public void setZfRate(String zfRate) {
        this.zfRate = zfRate;
    }

    private String zfRate;

}
