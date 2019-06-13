package com.wangxy.site.article.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import java.io.Serializable;

/**
* <p>
    * 频道
    * </p>
*
* @author wangxy
* @since 2019-06-09
*/
    @TableName("tb_channel")
    public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 频道名称
            */
    private String name;

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
        public String getState() {
        return state;
        }

            public void setState(String state) {
        this.state = state;
        }

    @Override
    public String toString() {
    return "Channel{" +
            "name=" + name +
            ", state=" + state +
    "}";
    }
}
