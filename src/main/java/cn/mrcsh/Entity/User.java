package cn.mrcsh.Entity;

import cn.mrcsh.Annotations.ExcelFieldName;
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
    @ExcelFieldName("用户ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id; // id
    /**
     * 用户名
     */
    @ExcelFieldName("用户名")
    @TableField(value = "username")
    private String username; // 用户名
    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    @ExcelFieldName("昵称")
    private String nick_name;
    /**
     * 密码
     */
    @TableField(value = "password")
    @ExcelFieldName("密码")
    private String password; // 密码
    /**
     * 角色
     */
    @TableField(value = "role")
    @ExcelFieldName("角色")
    private String role; // 角色
    /**
     * 部门
     */
    @TableField(value = "department")
    @ExcelFieldName("部门")
    private String department; // 部门

    @TableField(value = "used")
    @ExcelFieldName("是否启用")
    private boolean used;

    @TableField(value = "image_url")
    @ExcelFieldName("image_url")
    private String image_url;
    /**
     * Token
     */
    @TableField(exist = false)
    @ExcelFieldName("Token")
    private String Token; // Token
}
