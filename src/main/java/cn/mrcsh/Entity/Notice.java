package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("sys_notice")
public class Notice {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private long id;
    /**
     * 内容
     */
    @TableField("context")
    private String context;
    /**
     * 超时时间
     */
    @TableField("Timeout")
    private Long Timeout;
    /**
     * 类型
     */
    @TableField("type")
    private String type;
    /**
     * 状态
     */
    @TableField("statue")
    private boolean statue;

    @TableField("cdate")
    private String cdate;
}
