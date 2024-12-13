package org.jetlinks.sdk.generator.java;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import org.junit.jupiter.api.Test;
import org.springframework.core.ResolvableType;

import java.io.Serializable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JavaGeneratorTest {
    @Test
    void test1() {
        // 创建一个 CompilationUnit
        CompilationUnit cu = new CompilationUnit();

        // 创建一个类
        ClassOrInterfaceDeclaration myClass = cu.addClass("MyClass");

        // 创建字段类型和字段名
        Type fieldType = new ClassOrInterfaceType(null, "List<String>");
        String fieldName = "groupId";

        // 创建字段
        FieldDeclaration field = myClass.addField(fieldType, fieldName, Modifier.Keyword.PRIVATE);

        // 添加 @NotBlank 注解
        AnnotationExpr notBlankAnnotation = new MarkerAnnotationExpr("NotBlank");
        field.addAnnotation(notBlankAnnotation);

        // 创建 @Schema 注解并设置 description 属性
        NormalAnnotationExpr schemaAnnotation = new NormalAnnotationExpr();
        schemaAnnotation.setName("Schema");
        schemaAnnotation.addPair("description", new StringLiteralExpr("分组id"));

        field.addAnnotation(schemaAnnotation);

        // 输出生成的代码
        System.out.println(cu.toString());
    }

    @Test
    void test() {
        String java = JavaGenerator
            .create("org.jetlinks.test.Test")
            .implement(ResolvableType.forClass(Serializable.class))
            .comments("测试类",
                      "@author zhouhao")
            .addImport(List.class)
            .addMethod("setName", method -> {

                method.setType("Test");

                method
                    .addParameter(String.class, "name")
                    .createBody()
                    .addStatement("this.name=name;")
                    .addStatement("return this;")
                ;
            }).generate();

        assertNotNull(java);
        System.out.println(java);

    }
}