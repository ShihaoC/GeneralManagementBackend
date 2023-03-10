package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_menu_connect")
public class MenuConnect {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField("menu_id")
    private int menu_id;
    @TableField("role_id")
    private int role_id;
}
