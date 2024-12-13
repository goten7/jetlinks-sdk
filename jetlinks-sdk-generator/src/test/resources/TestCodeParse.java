package org.jetlinks.pro.devtools.impl.file.crud.spec;

import lombok.Getter;
import lombok.Setter;
import org.jetlinks.core.metadata.*;

import javax.validation.constraints.NotBlank;

/**
 * 属性
 *
 * @author gyl
 * @since 2.3
 */
@Getter
@Setter
public class PropertySpec {
    /**
     * 属性
     */
    @NotBlank
    private String property;

    /**
     * 注释
     */
    private String description;

    /**
     * 字段类型
     */
    @NotBlank
    private DataType type;
}
