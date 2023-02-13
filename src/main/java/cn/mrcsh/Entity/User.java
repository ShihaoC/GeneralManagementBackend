package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
