package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sys_authority")
@Data
public class Authority {
    @TableField("id")
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

}
