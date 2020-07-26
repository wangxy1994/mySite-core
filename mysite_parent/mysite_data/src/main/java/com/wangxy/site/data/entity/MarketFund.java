package com.wangxy.site.data.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangxy
 * @since 2019-07-17
 */
@TableName("tb_market_fund")
public class MarketFund implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer dataDate;

    private String code;

    private BigDecimal accumulatedNet;

    private BigDecimal todayNet;

    private BigDecimal bak;

    public Integer getDataDate() {
        return dataDate;
    }

    public void setDataDate(Integer dataDate) {
        this.dataDate = dataDate;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public BigDecimal getAccumulatedNet() {
        return accumulatedNet;
    }

    public void setAccumulatedNet(BigDecimal accumulatedNet) {
        this.accumulatedNet = accumulatedNet;
    }
    public BigDecimal getTodayNet() {
        return todayNet;
    }

    public void setTodayNet(BigDecimal todayNet) {
        this.todayNet = todayNet;
    }
    public BigDecimal getBak() {
        return bak;
    }

    public void setBak(BigDecimal bak) {
        this.bak = bak;
    }

    @Override
    public String toString() {
        return "MarketFund{" +
        "dataDate=" + dataDate +
        ", code=" + code +
        ", accumulatedNet=" + accumulatedNet +
        ", todayNet=" + todayNet +
        ", bak=" + bak +
        "}";
    }
}
