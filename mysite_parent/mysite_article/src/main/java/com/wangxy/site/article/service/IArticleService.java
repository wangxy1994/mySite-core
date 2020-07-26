package com.wangxy.site.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wangxy.site.article.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
public interface IArticleService extends IService<Article> {

    PageInfo<Article> findSearch(Map searchMap, int page, int size);
    List<Article> findSearch(Map searchMap);

    Article findById(String id);
}
