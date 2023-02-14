package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
