package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * <h3>用户实体类</h3>
 * <hr/>
 * 数据库: sys_user
 * <br/>
 * <br/>
 * <br/>
 */
@Data
@TableName("sys_user")
public class User {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id; // id
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username; // 用户名
    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nick_name;
    /**
     * 密码
     */
    @TableField(value = "password")
    private String password; // 密码
    /**
     * 角色
     */
    @TableField(value = "role")
    private String role; // 角色
    /**
     * 部门
     */
    @TableField(value = "department")
    private String department; // 部门
    /**
     * Token
     */
    @TableField(exist = false)
    private String Token; // Token
}
