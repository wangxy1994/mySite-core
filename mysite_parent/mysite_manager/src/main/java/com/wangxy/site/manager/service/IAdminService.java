package com.wangxy.site.manager.service;

import com.github.pagehelper.PageInfo;
import com.wangxy.site.manager.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
public interface IAdminService extends IService<Admin> {
    public PageInfo<Admin> findSearch(Map<String,Object> whereMap, int currentPage, int pageSize) ;

    Admin findByLoginnameAndPassword(String loginname, String password);

    void add(Admin admin);

    Admin findByLoginName(String loginName);
}
