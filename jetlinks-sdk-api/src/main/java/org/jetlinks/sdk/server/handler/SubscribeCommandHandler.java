package org.jetlinks.sdk.server.handler;

/**
 * @author gyl
 * @since 2.2
 */

import lombok.extern.slf4j.Slf4j;
import org.jetlinks.sdk.server.commons.cmd.SubscribeCommand;
import org.springframework.util.CollectionUtils;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

@Slf4j
public class SubscribeCommandHandler {
    protected final Map<Class<?>, List<Function<Object, Mono<Void>>>> callbacks = new ConcurrentHashMap<>();

    public <T, CMD extends SubscribeCommand<T, CMD>> Disposable addCallback(Class<T> eventClass,
                                                                            CMD cmd,
                                                                            Function<T, Mono<T>> callback) {
        return addCallback0(eventClass, obj -> callback.apply(eventClass.cast(obj)).then());
    }

    public <T> Disposable addCallback0(Class<T> eventClass,
                                       Function<Object, Mono<Void>> callback) {
        callbacks
                .computeIfAbsent(eventClass, ignore -> new CopyOnWriteArrayList<>())
                .add(callback);

        return () -> callbacks
                .compute(eventClass, (k, list) -> {
                    if (CollectionUtils.isEmpty(list)) {
                        return null;
                    }
                    list.remove(callback);
                    if (list.isEmpty()) {
                        return null;
                    }
                    return list;
                });

    }

    public Mono<Void> handle(Object event) {
        return handle(event, callbacks.getOrDefault(event.getClass(), Collections.emptyList()));
    }

    private static Mono<Void> handle(Object event, List<Function<Object, Mono<Void>>> functions) {
        if (CollectionUtils.isEmpty(functions)) {
            return Mono.empty();
        }
        return Flux
                .fromIterable(functions)
                .flatMap(callback -> callback.apply(event))
                .then();
    }
}