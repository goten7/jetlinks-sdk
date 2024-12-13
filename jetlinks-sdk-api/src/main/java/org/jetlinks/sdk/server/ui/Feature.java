package org.jetlinks.sdk.server.ui;

import java.lang.annotation.*;

/**
 * @author gyl
 * @since 2.3
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Feature {

    String key();

    String value() default "";
}
