package com.wangxy.site.manager.mapper;

import com.wangxy.site.manager.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
public interface UserMapper extends BaseMapper<User> {

    User findByMail(String mail);
}
