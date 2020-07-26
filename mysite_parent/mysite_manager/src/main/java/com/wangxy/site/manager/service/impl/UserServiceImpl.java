package com.wangxy.site.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxy.site.manager.entity.User;
import com.wangxy.site.manager.mapper.UserMapper;
import com.wangxy.site.manager.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.*;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public PageInfo<User> findSearch(Map whereMap, int page, int size) {

        PageHelper.startPage(page, size);
        List<User> userList =  baseMapper.selectByMap(whereMap);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }


//    /**
//     * 条件查询
//     * @param whereMap
//     * @return
//     */
//    public List<User> findSearch(Map whereMap) {
//        Specification<User> specification = createSpecification(whereMap);
//        return baseMapper.findAll(specification);
//    }

    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public User findById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 增加
     * @param user 用户
     * @param code 用户填写的验证码
     */
    public void add(User user,String code) {
        //判断验证码是否正确
		
		String syscode = (String)redisTemplate.opsForValue().get("smscode_" + user.getEmail()); //提取系统正确的验证码
		if(syscode==null){
			throw new RuntimeException("请点击获取邮件验证码");
		}
		if(!syscode.equals(code)){
			throw new RuntimeException("验证码输入不正确");
		}

        user.setId( idWorker.nextId()+"" );
        user.setFollowcount(0);//关注数
        user.setFanscount(0);//粉丝数
        user.setOnline(0L);//在线时长
        user.setRegdate(new Date());//注册日期
        user.setUpdatedate(new Date());//更新日期
        user.setLastdate(new Date());//最后登陆日期

        String newpassword = encoder.encode(user.getPassword());
        user.setPassword(newpassword);

        baseMapper.insert(user);
    }

    /**
     * 增加
     * @param user 用户
     */
    public void add(User user) {

        user.setId( idWorker.nextId()+"" );

        String newpassword = encoder.encode(user.getPassword());
        user.setPassword(newpassword);
        baseMapper.insert(user);
    }

    /**
     * 修改
     * @param user
     */
    public void update(User user) {
        baseMapper.updateById(user);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id) {
        baseMapper.deleteById(id);
    }

    /**
     * 发送邮件验证码
     * @param mail 邮箱
     */
    public void sendSms(String mail){
        //1.生成6位邮件验证码
        Random random=new Random();
        int max=999999;//最大数
        int min=100000;//最小数
        int code = random.nextInt(max);//随机生成
        if(code<min){
            code=code+min;
        }
        System.out.println(mail+"收到验证码是："+code);
        //2.将验证码放入redis
        redisTemplate.opsForValue().set("smscode_"+mail, code+"" ,5, TimeUnit.MINUTES );//五分钟过期
        

        //3.将验证码和邮箱发动到rabbitMQ中
        Map<String,String> map=new HashMap();
        map.put("mail",mail);
        map.put("code",code+"");
        rabbitTemplate.convertAndSend("sms",map);
    }




    /**
     * 根据邮箱和密码查询用户
     * @param mail
     * @param password
     * @return
     */
    public User findByMailAndPassword(String mail, String password){
        User user = baseMapper.findByMail(mail);
        if(user==null){
            return null;
        }
        if( encoder.matches(password,user.getPassword()) ){
            return user;
        }else{
            return null;
        }

    }

//    /**
//     * 变更粉丝数
//     * @param userid
//     * @param x
//     */
//    @Transactional
//    public void incFanscount(String userid,int x){
//        baseMapper.incFanscount(userid,x);
//    }
//
//    @Transactional
//    public void incFollowcount(String userid,int x){
//        baseMapper.incFollowcount(userid,x);
//    }

}
