package cn.mrcsh.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("sys_employee")
public class Employee {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 员工姓名
     */
    @TableField("name")
    private String name;
    /**
     * 入职日期
     */
    @TableField("join_data")
    private Date join_data;
    /**
     * 岗位
     */
    @TableField("department")
    private String department;
    /**
     * 是否打卡
     */
    @TableField("clockin")
    private boolean clockin;
    /**
     * 此工程工数
     */
    @TableField("work_num")
    private Integer work_num;
    /**
     * 是否离职
     */
    @TableField("quit")
    private boolean quit;
    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;
}
