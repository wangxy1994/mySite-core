package com.wangxy.site.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxy.site.manager.entity.Admin;
import com.wangxy.site.manager.mapper.AdminMapper;
import com.wangxy.site.manager.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IdWorker idWorker;
    /**
     * 条件查询+分页
     * @param whereMap
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageInfo<Admin> findSearch(Map<String,Object> whereMap, int currentPage, int pageSize) {
//        Map<String,Object> map = new HashMap<>();

        PageHelper.startPage(currentPage, pageSize);
        List<Admin> adminList =  baseMapper.selectByMap(whereMap);
        PageInfo<Admin> pageInfo = new PageInfo<>(adminList);
        return pageInfo;
    }


//    /**
//     * 条件查询
//     * @param whereMap
//     * @return
//     */
//    public List<Admin> findSearch(Map whereMap) {
//        Specification<Admin> specification = createSpecification(whereMap);
//        return baseMapper.findAll(specification);
//    }

    /**
     * 增加
     * @param admin
     */
    public void add(Admin admin) {
        admin.setId( idWorker.nextId()+"" );
        String newpassword = encoder.encode(admin.getPassword());//对密码进行加密
        admin.setPassword(newpassword);
        baseMapper.insert(admin);
    }
//
    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public Admin findById(String id) {
        return baseMapper.selectById(id);
    }
    /**
     * 根据登录用户名查询实体
     * @param loginName
     * @return
     */
    public Admin findByLoginName(String loginName) {
        return baseMapper.findByLoginname(loginName);
    }


    /**
     * 根据用户名和密码查询管理员
     * @param loginname
     * @param password
     * @return
     */
    public Admin findByLoginnameAndPassword(String loginname,String password){
        Admin admin = baseMapper.findByLoginname(loginname);
        if(admin==null){
            return null;
        }
        if(encoder.matches(password, admin.getPassword())){//如果匹配
            return admin;
        }else{
            return null;
        }
    }
}
