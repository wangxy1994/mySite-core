package com.wangxy.site.manager.service.impl;

import com.wangxy.site.manager.entity.User;
import com.wangxy.site.manager.mapper.UserMapper;
import com.wangxy.site.manager.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
