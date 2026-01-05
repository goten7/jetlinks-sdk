package org.jetlinks.sdk.server.ai;

import com.alibaba.fastjson.serializer.JSONSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetlinks.sdk.server.ai.cv.ObjectDetectionCommand;
import org.jetlinks.sdk.server.ai.cv.ObjectDetectionResult;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.Type;

@AllArgsConstructor
@Getter
public enum InternalTaskTarget implements TaskTarget {

    /* =- 计算机视觉 -= */

    /**
     * @see ObjectDetectionResult
     * @see org.jetlinks.sdk.server.ai.cv.ObjectDetectionCommand
     */
    ObjectDetection("目标检测", ObjectDetectionCommand.aiOutputMetadata),

    ImageRecognition("图像识别", ObjectDetectionCommand.aiOutputMetadata),
//
//    /* =- 自然语言处理 -= */
//    TextClassification("文本分类"),
//    SpeechRecognition("语音识别"),
//    TextGeneration("文本生成"),
//    LLM("大语言模型"),
//    LLMAgent("大语言模型智能体"),
//    /* =- 语音处理 -= */
//    SpeechSynthesis("语音合成"),
//    SpeechTranslation("语音翻译"),

    ;
    private final String text;
    private final String description;
    @Nullable
    private final AiOutputMetadata metadata;

    InternalTaskTarget(String text, @Nullable AiOutputMetadata metadata) {
        this.text = text;
        this.description = text;
        this.metadata = metadata;
    }

    public Mono<AiOutputMetadata> getAiOutputMetadata() {
        return Mono.justOrEmpty(metadata);
    }

    @Override
    public String getValue() {
        return name();
    }
}
