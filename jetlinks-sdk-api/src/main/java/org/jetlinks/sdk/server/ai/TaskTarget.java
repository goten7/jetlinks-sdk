package org.jetlinks.sdk.server.ai;

import reactor.core.publisher.Mono;

/**
 * AI 任务目标
 *
 * @see InternalTaskTarget
 * @see AiCommand
 * @since 1.0.1
 */
public interface TaskTarget {

    String getValue();

    String getText();

    String getDescription();

    default Mono<AiOutputMetadata> getAiOutputMetadata() {
        return Mono.empty();
    }

}
