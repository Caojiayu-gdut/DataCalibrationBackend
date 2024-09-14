package org.example.LEB.entity.deviceEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.LEB.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Document("COBRA MK2")
public class CobraMk2Entity extends BaseEntity {

    @JsonProperty("PAN")
    private String PAN;

    @JsonProperty("TITL")
    private String TITL;

    @JsonProperty("DIMMER")
    private String DIMMER;


    @JsonProperty("ZOOM")
    private String ZOOM;

    @JsonProperty("FOCUS")
    private String FOCUS;

    @JsonProperty("COLOR")
    private String COLOR;

    @JsonProperty("COLOR_MOVE")
    @Field("COLOR MOVE")
    private String COLOR_MOVE;

    @JsonProperty("CYAN")
    private String CYAN;

    @JsonProperty("MAGENTA")
    private String MAGENTA;

    @JsonProperty("YELLOW")
    private String YELLOW;

    @JsonProperty("GOBO1")
    private String GOBO1;

    @JsonProperty("OPEN")
    private String OPEN;

    @JsonProperty("GOBO1ROT1")
    private String GOBO1ROT1;

    @JsonProperty("GOBO1ROT2")
    private String GOBO1ROT2;

    @JsonProperty("GOBO1ROT3")
    private String GOBO1ROT3;

    @JsonProperty("GOBO1ROT4")
    private String GOBO1ROT4;

    @JsonProperty("GOBO1ROT5")
    private String GOBO1ROT5;

    @JsonProperty("GOBO1ROT6")
    private String GOBO1ROT6;

    @JsonProperty("GOBO1ROT7")
    private String GOBO1ROT7;

    @JsonProperty("GOBO1ROT8")
    private String GOBO1ROT8;

    @JsonProperty("GOBO1ROT9")
    private String GOBO1ROT9;

    @JsonProperty("GOBO1ROT10")
    private String GOBO1ROT10;

    public static final String[] FIELD_ORDER = {"id", "ICID", "productSN","variableParams","createdAt","updatedAt","PAN", "TITL", "DIMMER","ZOOM", "FOCUS", "COLOR",
            "COLOR_MOVE", "CYAN", "MAGENTA", "YELLOW", "GOBO1","OPEN","GOBO1ROT1", "GOBO1ROT2", "GOBO1ROT3",
            "GOBO1ROT4", "GOBO1ROT5", "GOBO1ROT6", "GOBO1ROT7", "GOBO1ROT8", "GOBO1ROT9", "GOBO1ROT10"};
}
