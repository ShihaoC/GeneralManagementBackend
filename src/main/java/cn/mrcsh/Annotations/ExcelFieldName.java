package cn.mrcsh.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置导出Excel列名
 */
@Target({ElementType.FIELD}) // 只能写在字段上
@Retention(RetentionPolicy.RUNTIME) // 运行时可用
public @interface ExcelFieldName {
    String value();
    boolean exclude() default false;
}