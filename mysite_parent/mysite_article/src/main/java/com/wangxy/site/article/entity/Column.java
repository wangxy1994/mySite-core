package com.wangxy.site.article.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import java.time.LocalDateTime;
    import java.io.Serializable;

/**
* <p>
    * 专栏
    * </p>
*
* @author wangxy
* @since 2019-06-09
*/
    @TableName("tb_column")
    public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 专栏名称
            */
    private String name;

            /**
            * 专栏简介
            */
    private String summary;

            /**
            * 用户ID
            */
    private String userid;

            /**
            * 申请日期
            */
    private LocalDateTime createtime;

            /**
            * 审核日期
            */
    private LocalDateTime checktime;

            /**
            * 状态
            */
    private String state;

        public String getName() {
        return name;
        }

            public void setName(String name) {
        this.name = name;
        }
        public String getSummary() {
        return summary;
        }

            public void setSummary(String summary) {
        this.summary = summary;
        }
        public String getUserid() {
        return userid;
        }

            public void setUserid(String userid) {
        this.userid = userid;
        }
        public LocalDateTime getCreatetime() {
        return createtime;
        }

            public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
        }
        public LocalDateTime getChecktime() {
        return checktime;
        }

            public void setChecktime(LocalDateTime checktime) {
        this.checktime = checktime;
        }
        public String getState() {
        return state;
        }

            public void setState(String state) {
        this.state = state;
        }

    @Override
    public String toString() {
    return "Column{" +
            "name=" + name +
            ", summary=" + summary +
            ", userid=" + userid +
            ", createtime=" + createtime +
            ", checktime=" + checktime +
            ", state=" + state +
    "}";
    }
}
