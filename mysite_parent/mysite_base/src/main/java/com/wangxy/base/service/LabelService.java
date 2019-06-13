package com.wangxy.base.service;

import com.github.pagehelper.PageHelper;
import com.wangxy.base.mapper.LabelMapper;
import com.wangxy.base.pojo.Label;
import com.wangxy.base.pojo.LabelExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    LabelMapper labelMapper;
    @Autowired
    IdWorker idWorker;

    public List<Label> findAll(){
        //1、设置分页信息，包括当前页数和每页显示的总计数
        PageHelper.startPage(1, 3);
        //2、执行查询
        return labelMapper.selectByExample(null);
    }

    public Label findById(String id){
        return labelMapper.selectByPrimaryKey(id);
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelMapper.insert(label);
    }

    public void update(Label label){
        labelMapper.updateByExampleSelective(label,new LabelExample());
    }


    public int deleteById(String id){
        return labelMapper.deleteByPrimaryKey(id);
    }






}
