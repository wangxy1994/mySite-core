package com.wangxy.site.manager.controller;


import com.github.pagehelper.PageInfo;
import com.wangxy.site.manager.entity.User;
import com.wangxy.site.manager.service.IUserService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
@RequestMapping("/user/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpServletRequest request;
    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",userService.list());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",userService.getById(id));
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
        PageInfo<User> pageList = userService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",   new PageResult<User>(pageList.getTotal(),pageList.getList()) );
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
//    @RequestMapping(value="/search",method = RequestMethod.POST)
//    public Result findSearch( @RequestBody Map searchMap){
//        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
//    }

    /**
     * 增加
     * @param user
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody User user  ){
        userService.save(user);
        return new Result(true,StatusCode.OK,"增加成功");
    }



    /**
     * 注册
     * @param user
     */
    @RequestMapping(value="/register/{code}",  method=RequestMethod.POST)
    public Result register(@RequestBody User user ,@PathVariable String code ){
        userService.add(user,code);
        return new Result(true,StatusCode.OK,"用户注册成功");
    }

    /**
     * 修改
     * @param user
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id ){
        user.setId(id);
        userService.updateById(user);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        Claims claims= (Claims)request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false,StatusCode.ACCESSERROR,"权限不足");
        }

        userService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }


    /**
     * 发送邮件验证码
     * @param mail
     * @return
     */
    @RequestMapping(value="/sendsms/{mail:.+}" ,method = RequestMethod.PUT)
    public Result sendSms(@PathVariable String mail){
        userService.sendSms(mail);//发送
        return new Result(true,StatusCode.OK,"发送成功");
    }

    @Autowired
    private JwtUtil jwtUtil;

    /**
     *  用户登陆
     * @param loginMap
     * @return
     */
    @RequestMapping(value="/login" ,method = RequestMethod.POST)
    public Result login( @RequestBody Map<String,String> loginMap ){
        User user = userService.findByMailAndPassword(loginMap.get("username"), loginMap.get("password"));
        if(user!=null){

            //签发token
            String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
            Map map=new HashMap();
            map.put("token",token);
            map.put("name",user.getNickname());
            map.put("avatar",user.getAvatar());

            return new Result(true,StatusCode.OK,"登陆成功",map);
        }else{
            return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
        }
    }


/*

    @RequestMapping(value="/incfans/{userid}/{x}",method = RequestMethod.POST)
    public void incFanscount(@PathVariable String userid,@PathVariable  int x){
        userService.incFanscount(userid,x);
    }

    @RequestMapping(value="/incfollow/{userid}/{x}",method = RequestMethod.POST)
    public void incFollowcount(@PathVariable String userid,@PathVariable  int x){
        userService.incFollowcount(userid,x);
    }

*/

}
