package org.example.LEB.controller;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.lang.reflect.Field;


public class BaseSerializer <T>extends StdSerializer<T> {
    public BaseSerializer() {
        this(null);
    }
    public BaseSerializer(Class<T> t) {
        super(t);
    }

    @Override
    public void serialize(T BaseEntity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
    //    获取字段顺序定义
        try {
            String[] fieldOrder = (String[]) BaseEntity.getClass().getDeclaredField("FIELD_ORDER").get(null);
        // 使用指定顺序进行序列化
        for (String fieldName : fieldOrder) {
            Field field = getField(BaseEntity.getClass(),fieldName);
            if (field != null) {
                field.setAccessible(true);
                Object value = field.get(BaseEntity);
                if (value != null) {
                    jsonGenerator.writeObjectField(fieldName, value);
                }
                else {
                jsonGenerator.writeNullField(fieldName);
                }
            }
        }
    } catch (NoSuchFieldException | IllegalAccessException e) {
        throw new IOException("Error serializing entity", e);
    }
        jsonGenerator.writeEndObject();
    }

    private Field getField(Class<?> clazz, String fieldName){
        while (clazz != null) {
            try{
                return clazz.getDeclaredField(fieldName);
            }catch (NoSuchFieldException e){
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }
}
