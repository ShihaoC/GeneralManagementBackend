package cn.mrcsh.Entity;

import cn.mrcsh.Annotations.ExcelFieldName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * <h3>岗位实体类</h3>
 * <hr/>
 * 数据库: sys_department
 * <br/>
 * <br/>
 * <br/>
 */
@Data
@TableName("sys_department")
public class Department {
    /**
     * 部门id
     */
    @ExcelFieldName("编号")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 部门代号
     */
    @ExcelFieldName("部门代号")
    @TableField(value = "department")
    private String department;
    /**
     * 部门名称
     */
    @ExcelFieldName("部门名称")
    @TableField(value = "nick")
    private String nick;
    /**
     * 是否启用
     */
    @ExcelFieldName("是否启用")
    @TableField(value = "online")
    private boolean online;
    /**
     * 创建日期
     */
    @ExcelFieldName("创建日期")
    @TableField(value = "date")
    private Date date;
}
