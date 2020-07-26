package com.wangxy.site.article.controller;


import com.github.pagehelper.PageInfo;
import com.wangxy.site.article.entity.Column;
import com.wangxy.site.article.service.IColumnService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 专栏 前端控制器
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
@RestController
@RequestMapping("/column")
public class ColumnController {
    @Autowired
    private IColumnService columnService;


    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",columnService.list());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",columnService.getById(id));
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
        PageInfo<Column> pageList = columnService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Column>(pageList.getTotal(),pageList.getList()) );
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",columnService.findSearch(searchMap));
    }

    /**
     * 增加
     * @param column
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Column column  ){
        columnService.save(column);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param column
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody Column column, @PathVariable String id ){
        column.setId(id);
        columnService.updateById(column);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        columnService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
