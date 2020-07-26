package com.wangxy.site.manager.service;

import com.github.pagehelper.PageInfo;
import com.wangxy.site.manager.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
public interface IUserService extends IService<User> {

    void sendSms(String mobile);

    User findByMailAndPassword(String mobile, String password);

    public void add(User user,String code);

    public PageInfo<User> findSearch(Map whereMap, int page, int size);
}
