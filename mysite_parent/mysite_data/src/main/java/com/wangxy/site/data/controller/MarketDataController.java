package com.wangxy.site.data.controller;

import com.wangxy.site.data.ScheduledJob.MarketDataImport;
import com.wangxy.site.data.service.IFundListService;
import com.wangxy.site.data.service.IMarketFundService;
import entity.Result;
import entity.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping("/data/market")
public class MarketDataController {
    private final static Logger logger = LoggerFactory.getLogger(MarketDataController.class);
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 测试财经接口
     * @return
     */
    @RequestMapping("/sayhello2")
    public String sayHello2() {
        //1.创建访问url
        String url = "http://img1.money.126.net/data/{markretName}/time/today/{stockCode}.json";
        String testUrl = "http://img1.money.126.net/data/hs/time/today/1399001.json";
        //2.post请求的参数绑定
        MultiValueMap<String , Object> params = new LinkedMultiValueMap<>();
        params.add("markretName", "hs");
        params.add("stockCode", "1399001");

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(testUrl,String.class,params);
        System.out.println(responseEntity);
        return responseEntity.getBody();
    }

    @Autowired
    private IFundListService fundListService;
    @Autowired
    private IMarketFundService marketFundService;

    @RequestMapping("/importFundListData")
    public Result importFundListData() {
        int num = marketFundService.importFundListData();
        String message ="导入成功,共计"+num+"条";
        return new Result(true, StatusCode.OK,message);
    }

    @RequestMapping("/importHistoryData")
    @Transactional
    public Result importHistoryData() {
        int num = marketFundService.importHistoryData();
        String message ="导入成功,共计"+num+"条";
        return new Result(true, StatusCode.OK,message);
    }
    @RequestMapping("/importTodayData")
    @Transactional
    public Result importTodayData() {
        int num = marketFundService.importTodayData();
        String message ="导入成功,共计"+num+"条";
        return new Result(true, StatusCode.OK,message);
    }
}
