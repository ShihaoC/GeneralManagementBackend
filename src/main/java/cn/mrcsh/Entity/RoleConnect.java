package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sys_role_connect")
@Data
public class RoleConnect {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("role_id")
    private Integer role_id;
    @TableField("authority_id")
    private Integer authority_id;
    @TableField("enable")
    private boolean enable;
}
