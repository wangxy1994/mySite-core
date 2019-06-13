package com.wangxy.site.manager.mapper;

import com.wangxy.site.manager.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 管理员 Mapper 接口
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
public interface AdminMapper extends BaseMapper<Admin> {

    Admin findByLoginname(String loginname);
}
