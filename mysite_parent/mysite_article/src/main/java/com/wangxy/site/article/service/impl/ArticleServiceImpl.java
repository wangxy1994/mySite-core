package com.wangxy.site.article.service.impl;

import com.wangxy.site.article.entity.Article;
import com.wangxy.site.article.mapper.ArticleMapper;
import com.wangxy.site.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
