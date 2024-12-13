package org.jetlinks.sdk.server.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gyl
 * @since 2.3
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UI {

    /**
     * 是否开启动态页面支持
     */
    boolean auto() default false;

    /**
     * 其它特征
     */
    Feature[] features() default {};
}
