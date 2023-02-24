package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色实体类
 */
@Data
@TableName("sys_role")
public class Role {
    @TableField("id")
    private Integer id; // id
    @TableField("role_name")
    private String role_name; // 角色代号
    @TableField("nick")
    private String nick; // 角色名称
    @TableField("mark")
    private String mark; // 备注
    @TableField("enable")
    private boolean enable; // 是否启用
}
