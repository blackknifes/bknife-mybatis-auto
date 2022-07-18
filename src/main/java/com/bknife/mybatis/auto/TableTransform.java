package com.bknife.mybatis.auto;

import java.lang.reflect.Modifier;

import org.apache.ibatis.annotations.Select;

import com.bknife.mybatis.auto.annotations.Column;
import com.bknife.mybatis.auto.annotations.Table;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.utility.JavaModule;

public class TableTransform implements AgentBuilder.Transformer {

    private static String toStaticFieldName(String name) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("FIELD_");
        for (int i = 0; i < name.length(); ++i) {
            char ch = name.charAt(i);
            if (Character.isUpperCase(ch))
                buffer.append("_");
            buffer.append(ch);
        }

        return buffer.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder<?> transform(Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader,
            JavaModule module) {
        AnnotationList annotations = typeDescription.getDeclaredAnnotations();
        Table table = annotations.ofType(Table.class).load();

        for (FieldDescription field : typeDescription.getDeclaredFields()) {
            Column column = field.getDeclaredAnnotations().ofType(Column.class).load();
            if (column == null)
                continue;
            // 添加静态列名
            builder.defineField(field.getName(), String.class, Modifier.STATIC | Modifier.PUBLIC | Modifier.FINAL)
                    .value(column.name());
        }

        AnnotationDescription description = AnnotationDescription.Builder.ofType(Select.class)
                .defineArray("value", "SELECT * FROM " + table.name())
                .build();
        DynamicType.Builder<Object> mapperBuild = (Builder<Object>) new ByteBuddy().makeInterface()
                .name(typeDescription.getActualName() + "$Mapper")
                .modifiers(Modifier.PUBLIC | Modifier.STATIC)
                .defineMethod("list", typeDescription, Modifier.PUBLIC)
                .withoutCode()
                .annotateType(description)
                .make();

        mapperBuild.innerTypeOf(typeDescription).asMemberType();
        builder.declaredTypes(mapperBuild.toTypeDescription());

        return builder;
    }
}
