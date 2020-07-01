package com.wangxy.site.manager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 登陆名称
     */
    private String loginname;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生年月日
     */
    private LocalDateTime birthday;

    /**
     * 头像
     */
    private String avatar;

    /**
     * E-Mail
     */
    private String email;

    /**
     * 注册日期
     */
    private LocalDateTime regdate;

    /**
     * 修改日期
     */
    private LocalDateTime updatedate;

    /**
     * 最后登陆日期
     */
    private LocalDateTime lastdate;

    /**
     * 在线时长（分钟）
     */
    private Long online;

    /**
     * 兴趣
     */
    private String interest;

    /**
     * 个性
     */
    private String personality;

    /**
     * 粉丝数
     */
    private Integer fanscount;

    /**
     * 关注数
     */
    private Integer followcount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDateTime getRegdate() {
        return regdate;
    }

    public void setRegdate(LocalDateTime regdate) {
        this.regdate = regdate;
    }
    public LocalDateTime getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(LocalDateTime updatedate) {
        this.updatedate = updatedate;
    }
    public LocalDateTime getLastdate() {
        return lastdate;
    }

    public void setLastdate(LocalDateTime lastdate) {
        this.lastdate = lastdate;
    }
    public Long getOnline() {
        return online;
    }

    public void setOnline(Long online) {
        this.online = online;
    }
    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }
    public Integer getFanscount() {
        return fanscount;
    }

    public void setFanscount(Integer fanscount) {
        this.fanscount = fanscount;
    }
    public Integer getFollowcount() {
        return followcount;
    }

    public void setFollowcount(Integer followcount) {
        this.followcount = followcount;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", loginname=" + loginname +
        ", password=" + password +
        ", nickname=" + nickname +
        ", sex=" + sex +
        ", birthday=" + birthday +
        ", avatar=" + avatar +
        ", email=" + email +
        ", regdate=" + regdate +
        ", updatedate=" + updatedate +
        ", lastdate=" + lastdate +
        ", online=" + online +
        ", interest=" + interest +
        ", personality=" + personality +
        ", fanscount=" + fanscount +
        ", followcount=" + followcount +
        "}";
    }
}