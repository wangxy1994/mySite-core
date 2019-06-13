package com.wangxy.site.article.service.impl;

import com.wangxy.site.article.entity.Column;
import com.wangxy.site.article.mapper.ColumnMapper;
import com.wangxy.site.article.service.IColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
