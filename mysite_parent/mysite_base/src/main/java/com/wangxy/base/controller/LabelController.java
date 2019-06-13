package com.wangxy.base.controller;

import com.wangxy.base.pojo.Label;
import com.wangxy.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){

        List<Label> labelList = labelService.findAll();
        return new Result(true,StatusCode.OK,"查询成功",labelService.findAll());
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId){
        System.out.println(123);
//        System.out.println(312);
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true,StatusCode.OK,"保存成功");
    }
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result modify(@PathVariable String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result deleteByID(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
