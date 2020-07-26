package com.wangxy.site.data.entity;

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
@TableName("tb_fund_list")
public class FundList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String enName;

    private String chName;

    private String fundType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }
    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    @Override
    public String toString() {
        return "FundList{" +
        "code=" + code +
        ", enName=" + enName +
        ", chName=" + chName +
        ", fundType=" + fundType +
        "}";
    }
}
