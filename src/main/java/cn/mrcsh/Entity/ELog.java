package cn.mrcsh.Entity;

import cn.mrcsh.Annotations.ExcelFieldName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@TableName("sys_log")
public class ELog {
    @ExcelFieldName("ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @ExcelFieldName("模块名称")
    @TableField(value = "module")
    private String module;
    @ExcelFieldName("操作类型")
    @TableField(value = "type")
    private String type;
    @ExcelFieldName("请求方式")
    @TableField(value = "request_type")
    private String request_type;
    @ExcelFieldName("请求用户")
    @TableField(value = "user")
    private String user;
    @ExcelFieldName("ip地址")
    @TableField(value = "address")
    private String address;
    @ExcelFieldName("请求日期")
    @TableField(value = "date")
    private String date;


    public static ELog getInstance(Long id,String module,String type,String request_type,String user,String address){
        ELog log = new ELog();
        log.setId(id);
        log.setModule(module);
        log.setType(type);
        log.setRequest_type(request_type);
        log.setUser(user);
        log.setAddress(address);
        log.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return log;
    }
}
