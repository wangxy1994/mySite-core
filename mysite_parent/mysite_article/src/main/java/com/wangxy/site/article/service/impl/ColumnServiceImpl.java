package com.wangxy.site.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxy.site.article.entity.Column;
import com.wangxy.site.article.mapper.ColumnMapper;
import com.wangxy.site.article.service.IColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 专栏 服务实现类
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements IColumnService {
    @Autowired
    private IdWorker idWorker;

    /**
     * 条件查询+分页
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Column> findSearch(Map searchMap, int page, int size) {
        //        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(page, size);
        List<Column> channelList =  baseMapper.selectByMap(searchMap);
        PageInfo<Column> pageInfo = new PageInfo<>(channelList);
        return pageInfo;
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Column> findSearch(Map searchMap) {
        return baseMapper.selectByMap(searchMap);
    }
}
