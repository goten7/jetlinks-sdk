package org.jetlinks.sdk.server.device.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import org.jetlinks.core.command.AbstractCommand;
import org.jetlinks.core.command.CommandMetadataResolver;
import org.jetlinks.core.metadata.FunctionMetadata;
import org.jetlinks.sdk.server.commons.cmd.UnboundedResponseCommand;
import org.jetlinks.sdk.server.device.DeviceProperty;
import org.jetlinks.sdk.server.device.DevicePropertyProperties;
import org.jetlinks.sdk.server.ui.field.annotation.field.select.DevicePropertySelector;
import org.jetlinks.sdk.server.ui.field.annotation.field.select.DeviceSelector;
import org.jetlinks.sdk.server.ui.field.annotation.field.select.ProductSelector;
import org.jetlinks.sdk.server.utils.ConverterUtils;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Flux;

import java.util.List;

@Schema(title = "订阅设备全部属性数据", description = "根据参数设备的实时属性数据")
public class SubscribeDevicePropertiesCommand extends AbstractCommand<Flux<DevicePropertyProperties>, SubscribeDevicePropertiesCommand>
    implements UnboundedResponseCommand<DevicePropertyProperties> {

    public static final String DEVICE_IDS = "deviceIds";
    public static final String PRODUCT_ID = "productId";
    public static final String PROPERTY_ID = "propertyIds";
    public static final String HISTORY = "history";

    @DeviceSelector(multiple = true)
    @Order(1)
    @Schema(title = "设备ID集合",description = "为空订阅所有设备")
    public List<String> getDeviceIds() {
        return ConverterUtils
            .convertToList(readable().get(DEVICE_IDS), String::valueOf);
    }

    public SubscribeDevicePropertiesCommand setDeviceIds(List<String> deviceIds) {
        return with(DEVICE_IDS, deviceIds);
    }

    @ProductSelector
    @Schema(title = "产品ID")
    @Order(0)
    public String getProductId() {
        return getOrNull(PRODUCT_ID, String.class);
    }

    public SubscribeDevicePropertiesCommand setProductId(String productId) {
        return with(PRODUCT_ID, productId);
    }

    @DevicePropertySelector(multiple = true, deviceIdKey = DEVICE_IDS, productIdKey = PRODUCT_ID)
    @Schema(title = "属性ID集合", description = "为空订阅所有属性")
    @Order(3)
    public List<String> getPropertyIds() {
        return ConverterUtils
            .convertToList(readable().get(PROPERTY_ID), String::valueOf);
    }

    public SubscribeDevicePropertiesCommand setPropertyIds(List<String> propertyIds) {
        return with(PROPERTY_ID, propertyIds);
    }

    @Schema(title = "历史数据", description = "为空默认为0")
    public Integer getHistory() {
        return org.jetlinks.core.utils.ConverterUtils.convert(
                readable().getOrDefault(HISTORY, 0),
                Integer.class
        );
    }
}
