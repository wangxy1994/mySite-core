package com.wangxy.site.data.ScheduledJob;

import com.wangxy.site.data.service.IMarketFundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarketDataImport {
    private final static Logger logger = LoggerFactory.getLogger(MarketDataImport.class);

    @Autowired
    private IMarketFundService marketFundService;

        @Scheduled(cron = "0 3 19 * * *")
        public void scheduled(){
            logger.info("=====>>>>>开始导入基金今日行情数据 {}",System.currentTimeMillis());
            marketFundService.importTodayData();

        }
//        @Scheduled(fixedRate = 5000)
//        public void scheduled1() {
//            logger.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
//        }
//        @Scheduled(fixedDelay = 5000)
//        public void scheduled2() {
//            logger.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
//        }

}
