package com.wangxy.site.data.service;

import com.wangxy.site.data.entity.MarketFund;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangxy
 * @since 2019-07-17
 */
public interface IMarketFundService extends IService<MarketFund> {

    /**
     * 导入今日行情
     * 供定时任务调用
     * @return
     */
    public int importTodayData();
    /**
     * 导入基金历史行情数据
     * @return
     */
    public int importHistoryData();

    /**
     * 导入需要持久化的基金代码
     * @return
     */
    public int importFundListData();

}
