package cn.mrcsh.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Memory {
    /**
     * 属性
     */
    private String prop;
    /**
     * 值
     */
    private String value;
}
