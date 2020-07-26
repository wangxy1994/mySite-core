package com.wangxy.site.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wangxy.site.data.controller.MarketDataController;
import com.wangxy.site.data.entity.FundList;
import com.wangxy.site.data.entity.MarketFund;
import com.wangxy.site.data.mapper.MarketFundMapper;
import com.wangxy.site.data.service.IFundListService;
import com.wangxy.site.data.service.IMarketFundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangxy
 * @since 2019-07-17
 */
@Service
public class MarketFundServiceImpl extends ServiceImpl<MarketFundMapper, MarketFund> implements IMarketFundService {
    private final static Logger logger = LoggerFactory.getLogger(MarketDataController.class);
    @Autowired
    private IFundListService fundListService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 导入今日行情
     * 供定时任务调用
     * @return
     */
    @Transactional
    public int importTodayData() {
        //1.创建访问url
        String url = "http://fundgz.1234567.com.cn/js/001186.js?rt=1563728370076";
        String testUrl = "http://fundgz.1234567.com.cn/js/";
        String urlTail = ".js?rt=1563728370076";
        StringBuffer stringBuffer = null;
        int num = 0 ;
        //遍历目前存的基金代码表
        List<FundList> list = fundListService.list();
        for (int i = 0; i < list.size(); i++) {
            FundList fundList = list.get(i);
            String code = fundList.getCode();
            stringBuffer = new StringBuffer(testUrl);
            stringBuffer.append(code).append(urlTail);
            ResponseEntity<String> responseEntity = null;
            Map<String, Object> map = new HashMap<>();
            map.put("code",code);

            if(this.listByMap(map).size()>0){
                continue;
            }
            //形成完整链接，处理请求返回结果插入
            try {
                responseEntity = restTemplate.getForEntity(stringBuffer.toString(),String.class);
            } catch (RestClientException e) {
                e.printStackTrace();
                System.out.println("导入失败。代码："+code);
                MarketFund marketFund = new MarketFund();
                marketFund.setCode(code);
                marketFund.setDataDate(0);
                //System.out.println(marketFund);
                //this.save(marketFund);
                continue;
            }

            String jsonStr = responseEntity.toString().substring(responseEntity.toString().indexOf("{"),responseEntity.toString().indexOf("}")+1);


                JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
                //System.out.println(asJsonArray);

                MarketFund marketFund = new MarketFund();
                marketFund.setCode(code);
                marketFund.setDataDate(Integer.parseInt(jsonObject.get("jzrq").getAsString().replace("-","")));
                marketFund.setAccumulatedNet(jsonObject.get("dwjz").getAsBigDecimal());
                marketFund.setTodayNet(jsonObject.get("gsz").getAsBigDecimal());
                marketFund.setBak(jsonObject.get("gszzl").getAsBigDecimal());

                //System.out.println(marketFund);
                this.save(marketFund);
                num++;


        }

        System.out.println("共插入："+num);
        return num;

    }

    public int importHistoryData() {
        //1.创建访问url
        String url = "http://img1.money.126.net/data/fn/nav/150306.json?callback=_jsonp0";
        String testUrl = "http://img1.money.126.net/data/fn/nav/";
        String urlTail = ".json?callback=_jsonp0";
        StringBuffer stringBuffer = null;
        int num = 0 ;
        //遍历基金列表
        List<FundList> list = fundListService.list();
        for (int i = 0; i < list.size(); i++) {
            FundList fundList = list.get(i);
            String code = fundList.getCode();
            stringBuffer = new StringBuffer(testUrl);
            stringBuffer.append(code).append(urlTail);
            ResponseEntity<String> responseEntity = null;
            Map<String, Object> map = new HashMap<>();
            map.put("code",code);

            if(listByMap(map).size()>0){
                continue;
            }
            try {
                responseEntity = restTemplate.getForEntity(stringBuffer.toString(),String.class);
            } catch (RestClientException e) {
                //导入失败就进行下一个
                logger.error("代码："+code+"获取历史数据失败",e);
                MarketFund marketFund = new MarketFund();
                marketFund.setCode(code);
                marketFund.setDataDate(0);
                System.out.println(marketFund);
                //save(marketFund);
                continue;
            }

            String jsonStr = responseEntity.toString().substring(responseEntity.toString().indexOf("["),responseEntity.toString().indexOf("],\"")+1);
            System.out.println(jsonStr);
            JsonArray jsonArray = new JsonParser().parse(jsonStr).getAsJsonArray();
            for (int j = 0; j < jsonArray.size(); j++) {
                JsonArray asJsonArray = jsonArray.get(j).getAsJsonArray();

                //System.out.println(asJsonArray);
                MarketFund marketFund = new MarketFund();
                marketFund.setCode(code);
                marketFund.setDataDate(asJsonArray.get(0).getAsInt());
                marketFund.setAccumulatedNet(asJsonArray.get(1).getAsBigDecimal());
                marketFund.setTodayNet(asJsonArray.get(2).getAsBigDecimal());
                marketFund.setBak(asJsonArray.get(3).getAsBigDecimal());

                //System.out.println(marketFund);
                save(marketFund);
                num++;
            }
            logger.error("代码："+code+"导入历史数据成功");

        }
        System.out.println("共插入："+num);
        return num;

    }

    /**
     * 导入需要持久化的基金代码
     * @return
     */
    public int importFundListData() {
        //1.创建访问url
        String testUrl = "http://fund.eastmoney.com/js/fundcode_search.js";
        //2.post请求的参数绑定
        MultiValueMap<String , Object> params = new LinkedMultiValueMap<>();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(testUrl,String.class,params);
        System.out.println(responseEntity);
        System.out.println(responseEntity.toString().substring(responseEntity.toString().indexOf("["),responseEntity.toString().indexOf(";")));
        String jsonStr = responseEntity.toString().substring(responseEntity.toString().indexOf("["),responseEntity.toString().indexOf(";"));
        JsonArray jsonArray = new JsonParser().parse(jsonStr).getAsJsonArray();
        int importNum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonArray asJsonArray = jsonArray.get(i).getAsJsonArray();
            if ("股票型".equals(asJsonArray.get(3).getAsString())){
                System.out.println(asJsonArray);
                FundList fundList = new FundList();
                fundList.setCode(asJsonArray.get(0).getAsString());
                fundList.setEnName(asJsonArray.get(1).getAsString());
                fundList.setChName(asJsonArray.get(2).getAsString());
                fundList.setFundType(asJsonArray.get(3).getAsString());
                System.out.println(fundList);
                fundListService.save(fundList);
                importNum++;
            }

        }
        return importNum;
    }




}
