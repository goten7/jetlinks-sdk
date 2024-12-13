package org.jetlinks.sdk.server.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gyl
 * @since 2.3
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UIField {


    /**
     * 表单组件类型
     */
    String component();

    /**
     * 支持作用域，例如新增、编辑
     */
    String[] scope() default {};

    /**
     * 显示顺序
     */
    int order() default 0;

    /**
     * 其它特征
     */
    Feature[] features() default {};

}
