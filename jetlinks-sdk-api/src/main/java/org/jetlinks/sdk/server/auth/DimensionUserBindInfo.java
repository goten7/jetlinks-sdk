package org.jetlinks.sdk.server.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author gyl
 * @since 2.2
 */
@Getter
@Setter
@Schema(title = "维度用户绑定信息")
public class DimensionUserBindInfo extends DimensionUserBindRequset {

    @Schema(title = "用户id")
    private List<String> userId;
}
