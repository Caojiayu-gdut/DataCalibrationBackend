package org.example.LEB.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

//@JsonSerialize(using = BaseSerializer.class)
@Data
@Document
public abstract class BaseEntity {

    @Id
    @JsonProperty("id")
    @Field("_id")
    private ObjectId id;  // MongoDB 自带的主键字段

    @JsonProperty("ICID")
    @Field("ID")
    @Indexed(unique = true)
    private String ICID;    // 自定义的唯一 ID 字段，对应PCB主板编号

    @JsonProperty("variableParams")
    @Field("Variable Params")
    private LinkedHashMap<String, Integer> variableParams;

    @JsonProperty("productSN")
    @Field("Product SN")
    @Indexed(unique = true)
    private String productSN;    // Product SN,唯一属性

    @JsonProperty("createdAt")
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    @LastModifiedDate
    private LocalDateTime updatedAt;

//    private  String productSNs;
}

