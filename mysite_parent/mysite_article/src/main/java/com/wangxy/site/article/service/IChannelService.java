package com.wangxy.site.article.service;

import com.github.pagehelper.PageInfo;
import com.wangxy.site.article.entity.Channel;
import com.wangxy.site.article.entity.Channel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 频道 服务类
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
public interface IChannelService extends IService<Channel> {
    PageInfo<Channel> findSearch(Map searchMap, int page, int size);
    List<Channel> findSearch(Map searchMap);
}
