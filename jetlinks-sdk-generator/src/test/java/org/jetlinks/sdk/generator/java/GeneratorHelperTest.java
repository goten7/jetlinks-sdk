package org.jetlinks.sdk.generator.java;

import org.jetlinks.core.command.AbstractCommand;
import org.jetlinks.core.metadata.SimpleFunctionMetadata;
import org.jetlinks.core.metadata.SimplePropertyMetadata;
import org.jetlinks.core.metadata.types.ArrayType;
import org.jetlinks.core.metadata.types.IntType;
import org.jetlinks.core.metadata.types.ObjectType;
import org.jetlinks.core.metadata.types.StringType;
import org.junit.jupiter.api.Test;
import org.springframework.core.ResolvableType;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.util.Arrays;

class GeneratorHelperTest {

    public static void main(String[] args) {
        ResolvableType resolvableType = ResolvableType.forClassWithGenerics(
            AbstractCommand.class,
            ResolvableType.forClassWithGenerics(Flux.class, Serializable.class),
            ResolvableType.forClass(String.class)
        );
        System.out.println(resolvableType.toString());
    }


    @Test
    void test() {

        SimpleFunctionMetadata metadata = new SimpleFunctionMetadata();
        metadata.setName("Test");
        metadata.setId("test");
        metadata.setInputs(Arrays.asList(
            SimplePropertyMetadata.of("name", "名称", StringType.GLOBAL),
            SimplePropertyMetadata.of("age", "年龄", IntType.GLOBAL),
            SimplePropertyMetadata.of("address", "地址", StringType.GLOBAL),
            SimplePropertyMetadata.of("others", "其他信息", new ObjectType()),
            SimplePropertyMetadata.of("others2", "其他信息2", new ArrayType().elementType(StringType.GLOBAL))
        ));
        String clazz = GeneratorHelper
            .addCommandGetterSetter(
                JavaGenerator.create("org.jetlinks.plugin.commands.TestCommand"),
                metadata.getInputs()
            )
            .addImport(AbstractCommand.class)
            .addImport(Flux.class)
            .extendsClass(ResolvableType.forClassWithGenerics(
                AbstractCommand.class,
                ResolvableType.forClassWithGenerics(Flux.class, Serializable.class),
                ResolvableType.forClass(String.class)
            ))
//            .extendsClass("AbstractCommand<Flux<Object>,TestCommand>")
            .generate();

        System.out.println(clazz);
    }
}