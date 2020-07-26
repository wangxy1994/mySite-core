package com.wangxy.site.article.controller;

import com.github.pagehelper.PageInfo;
import com.wangxy.site.article.entity.Article;
import com.wangxy.site.article.service.IArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Article> articleList = articleService.list();
        return new Result(true, StatusCode.OK,"查询成功",articleService.list());
    }



    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",articleService.findById(id));
    }


    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        PageInfo<Article> pageList = articleService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Article>(pageList.getTotal(),pageList.getList()) );

    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",articleService.findSearch(searchMap));
    }

    /**
     * 增加
     * @param article
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Article article  ){
        articleService.save(article);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param article
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody Article article, @PathVariable String id ){
        article.setId(id);
        articleService.updateById(article);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        articleService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }


    /**
     * 审核
     * 只有自己发，不要审核
     * @param id
     * @return
     */
    @RequestMapping(value="/examine/{id}",method= RequestMethod.PUT)
    public Result examine( @PathVariable String id){
        Article article = new Article();
        article.setId(id);
        article.setState("1");
        articleService.updateById(article);
        return new Result(true,StatusCode.OK,"审核成功");
    }


}
