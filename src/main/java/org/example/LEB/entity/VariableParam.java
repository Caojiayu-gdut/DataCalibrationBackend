package org.example.LEB.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VariableParam {

    // Getters 和 Setters
    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;

    // 构造函数
    public VariableParam() {}

    public VariableParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

}

