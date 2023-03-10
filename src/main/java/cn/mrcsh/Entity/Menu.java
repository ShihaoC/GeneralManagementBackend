package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_menu")
public class Menu {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField("parent_id")
    private int parent_id;
    @TableField("name")
    private String name;
    @TableField("path")
    private String path;
    @TableField("icon")
    private String icon;
}
