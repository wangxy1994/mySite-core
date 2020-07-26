package com.wangxy.site.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxy.site.article.entity.Article;
import com.wangxy.site.article.mapper.ArticleMapper;
import com.wangxy.site.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public PageInfo<Article> findSearch(Map searchMap, int page, int size) {
        //        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(page, size);
        List<Article> articleList =  baseMapper.selectByMap(searchMap);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public List<Article> findSearch(Map searchMap) {
        return baseMapper.selectByMap(searchMap);
    }

    @Override
    public Article findById(String id) {
        //首先从缓存中查询，如果缓存中没有再从数据库中查询并放入缓存
        Article article=(Article)redisTemplate.opsForValue().get("article_"+id  );
        if(article==null){
            article=baseMapper.selectById(id);
            redisTemplate.opsForValue().set("article_"+id,article,1, TimeUnit.DAYS );//放入缓存(1天过期)
            redisTemplate.opsForValue().set("article_"+id,article,10, TimeUnit.SECONDS );//放入缓存(1天过期)
            System.out.println("从数据库查询记录并放入缓存");
        }else{
            System.out.println("从缓存中查询数据");
        }
        return article;
    }

}
