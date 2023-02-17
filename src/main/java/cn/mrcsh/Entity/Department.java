package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
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
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "department")
    private String department;
    @TableField(value = "nick")
    private String nick;
}
