package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sys_role_connect")
@Data
public class RoleConnect {
    @TableField("id")
    private Integer id;
    @TableField("role_id")
    private Integer role_id;
    @TableField("authority_id")
    private Integer authority_id;
    @TableField("enable")
    private boolean enable;
}
