package cn.mrcsh.Entity;

import cn.mrcsh.Annotations.ExcelFieldName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * <h3>员工实体类</h3>
 * <hr/>
 * 数据库: sys_employee
 * <br/>
 * <br/>
 * <br/>
 */
@Data
@TableName("sys_employee")
public class Employee {
    /**
     * id
     */
    @ExcelFieldName("ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 员工姓名
     */
    @ExcelFieldName("姓名")
    @TableField("name")
    private String name;
    /**
     * 入职日期
     */
    @ExcelFieldName("入职日期")
    @TableField("join_date")
    private Date join_date;
    /**
     * 岗位
     */
    @ExcelFieldName("岗位")
    @TableField("department")
    private String department;
    /**
     * 是否打卡
     */
    @ExcelFieldName("是否签到(今日)")
    @TableField("clockin")
    private boolean clockin;

    @ExcelFieldName("状态/签到/签退/未签到")
    @TableField("statue")
    private String statue;
    /**
     * 此工程工数
     */
    @ExcelFieldName("此工程的工数")
    @TableField("work_num")
    private Integer work_num;
    /**
     * 是否离职
     */
    @ExcelFieldName("是否离职")
    @TableField("quit")
    private boolean quit;
    /**
     * 手机号
     */
    @ExcelFieldName("手机号")
    @TableField("phone")
    private String phone;

    @ExcelFieldName("绑定的qq号")
    @TableField("bind_qq")
    private String bind_qq;
}
