package com.wangxy.site.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxy.site.article.entity.Channel;
import com.wangxy.site.article.mapper.ChannelMapper;
import com.wangxy.site.article.service.IChannelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 频道 服务实现类
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements IChannelService {

    /**
     * 条件查询+分页
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Channel> findSearch(Map searchMap, int page, int size) {
        //        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(page, size);
        List<Channel> channelList =  baseMapper.selectByMap(searchMap);
        PageInfo<Channel> pageInfo = new PageInfo<>(channelList);
        return pageInfo;
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Channel> findSearch(Map searchMap) {
        return baseMapper.selectByMap(searchMap);
    }


    
}
