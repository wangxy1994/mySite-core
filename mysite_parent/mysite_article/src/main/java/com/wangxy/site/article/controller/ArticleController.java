package com.wangxy.site.article.controller;


import com.wangxy.site.article.entity.Article;
import com.wangxy.site.article.service.IArticleService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Article> articleList = articleService.list();
        return new Result(true, StatusCode.OK,"查询成功",articleService.list());
    }
}
