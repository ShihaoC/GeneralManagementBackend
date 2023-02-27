package cn.mrcsh.Entity;

import cn.mrcsh.Annotations.ExcelFieldName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色实体类
 */
@Data
@TableName("sys_role")
public class Role {
    @TableId(value = "id",type = IdType.AUTO)
    @ExcelFieldName("角色ID")
    private Integer id; // id
    @TableField("role_name")
    @ExcelFieldName("角色代号")
    private String role_name; // 角色代号
    @TableField("nick")
    @ExcelFieldName("角色名称")
    private String nick; // 角色名称
    @TableField("mark")
    @ExcelFieldName("备注")
    private String mark; // 备注
    @TableField("enable")
    @ExcelFieldName("是否启用")
    private boolean enable; // 是否启用
}
