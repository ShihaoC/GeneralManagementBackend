package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@TableName("sys_authority")
@Data
@ToString
public class Authority {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("level")
    private Integer level;
    @TableField("name")
    private String name;
    @TableField("value")
    private String value;
    @TableField("path")
    private String path;
    @TableField("enable")
    private boolean enable;
    @TableField("type")
    private String type;
    @TableField("icon")
    private String icon;
    @TableField("menable")
    private boolean menable;
    @TableField("Exclusions")
    private boolean Exclusions;

    @TableField("sindex")
    private int sindex;

}
