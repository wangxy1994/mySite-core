package com.wangxy.site.article.service;

import com.github.pagehelper.PageInfo;
import com.wangxy.site.article.entity.Column;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 专栏 服务类
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
public interface IColumnService extends IService<Column> {
    PageInfo<Column> findSearch(Map searchMap, int page, int size);
    List<Column> findSearch(Map searchMap);
}
