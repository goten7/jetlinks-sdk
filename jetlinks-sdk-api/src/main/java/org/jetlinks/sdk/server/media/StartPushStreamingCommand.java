package org.jetlinks.sdk.server.media;

import io.swagger.v3.oas.annotations.media.Schema;
import org.jetlinks.core.command.AbstractCommand;
import org.jetlinks.core.command.CommandMetadataResolver;
import org.jetlinks.core.metadata.FunctionMetadata;
import org.jetlinks.sdk.server.utils.CastUtils;
import reactor.core.publisher.Mono;

@Schema(title = "开始推流")
public class StartPushStreamingCommand extends AbstractCommand<Mono<MediaStreamInfo>, StartPushStreamingCommand> {

    @Schema(description = "设备ID")
    public String getDeviceId() {
        return getOrNull("deviceId", String.class);
    }

    public StartPushStreamingCommand setDeviceId(String deviceId) {
        return with("deviceId", deviceId);
    }

    @Schema(description = "通道ID")
    public String getChannelId() {
        return getOrNull("channelId", String.class);
    }

    public StartPushStreamingCommand setChannelId(String channelId) {
        return with("channelId", channelId);
    }

    @Schema(description = "流ID")
    public String getStreamId() {
        return getOrNull("streamId", String.class);
    }

    public StartPushStreamingCommand setStreamId(String streamId) {
        return with("streamId", streamId);
    }

    //当指定了这个参数时,表示将设备的视频流同时转发到指定的目的地.
    @Schema(description = "推流目的地", example = "rtsp://127.0.0.1/live/stream?token=xxx")
    public String getDestination() {
        return getOrNull("destination", String.class);
    }

    public StartPushStreamingCommand setDestination(String destination) {
        return with("destination", destination);
    }

    @Schema(description = "SDP信息")
    public String getSdp() {
        return getOrNull("sdp", String.class);
    }

    public StartPushStreamingCommand setSdp(String sdp) {
        return with("sdp", sdp);
    }

    @Schema(description = "是否本地播放,true表示返回本地播放地址.")
    public boolean isLocalPlayer() {
        return CastUtils.castBoolean(readable().get("localPlayer"));
    }

    public StartPushStreamingCommand setLocalPlayer(boolean localPlayer) {
        with("localPlayer", localPlayer);
        return this;
    }

    // 录像相关参数
    @Schema(description = "推送录像视频流")
    public boolean isForRecord() {
        return CastUtils.castBoolean(readable().get("forRecord"));
    }

    public StartPushStreamingCommand setForRecord(boolean forRecord) {
        return with("forRecord", forRecord);
    }

    @Schema(description = "录像开始时间")
    public Long getStartWith() {
        return getOrNull("startWith", Long.class);
    }

    public StartPushStreamingCommand setStartWith(Long startWith) {
        return with("startWith", startWith);
    }

    @Schema(description = "录像结束时间")
    public Long getEndWith() {
        return getOrNull("endWith", Long.class);
    }

    public StartPushStreamingCommand setEndWith(Long endWith) {
        return with("endWith", endWith);
    }

    @Schema(description = "录像推流速度")
    public Integer getDownloadSpeed() {
        return getOrNull("downloadSpeed", Integer.class);
    }


    @Schema(description = "流所属应用")
    public String getApp() {
        return getOrNull("app", String.class);
    }

    public StartPushStreamingCommand setApp(String app) {
        return with("app", app);
    }

    @Schema(description = "是否为云端录像")
    public boolean isServerRecord() {
        return CastUtils.castBoolean(readable().get("serverRecord"));
    }

    public StartPushStreamingCommand setServerRecord(boolean serverRecord) {
        return with("serverRecord", serverRecord);
    }

    public StartPushStreamingCommand setDownloadSpeed(Integer downloadSpeed) {
        return with("downloadSpeed", downloadSpeed);
    }

    public boolean isLive() {
        return getStartWith() == null || getEndWith() == null;
    }

    public static FunctionMetadata metadata() {
        return CommandMetadataResolver.resolve(StartPushStreamingCommand.class);
    }

}
