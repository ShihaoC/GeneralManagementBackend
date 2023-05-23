package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@TableName("sys_mcserver")
public class mcserver {
    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private String ip;
}
