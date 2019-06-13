package com.wangxy.site.manager.controller;


import com.wangxy.site.manager.entity.User;
import com.wangxy.site.manager.service.IUserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
@RestController
@RequestMapping("/manager/user")
public class UserController {
    @Autowired
    private IUserService userService;



}
