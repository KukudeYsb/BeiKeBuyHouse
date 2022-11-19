package buymyhome.com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.sql.Date;

/**
 * @author 杨世博
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor


//@Data         不包括构造方法

public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像
     */
    private String head;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 数据创建时间
     */
    private Date createTime;
    /**
     * 数据修改时间
     */
    private Date updateTime;
    /**
     *  是否删除
     */
    private Integer isDelete;
}
