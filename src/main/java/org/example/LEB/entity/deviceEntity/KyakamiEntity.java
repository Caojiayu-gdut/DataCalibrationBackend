package org.example.LEB.entity.deviceEntity;

import lombok.Data;
import org.example.LEB.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data
@Document("KYALAMI")
public class KyakamiEntity extends BaseEntity {
    @Id
    private String PAN;
    private String TITL;
    private String DIMMER;
    private String ZOOM;
    private String FOCUS;
    private String COLOR;
    @Field("COLOR MOVE")
    private String COLOR_MOVE;
    private String CYAN;
    private String MAGENTA;
    private String YELLOW;
    private String GOBO1;
    private String OPEN;
    private String GOBO1ROT1;
    private String GOBO1ROT2;
    private String GOBO1ROT3;
    private String GOBO1ROT4;
    private String GOBO1ROT5;
    private String GOBO1ROT6;
    private String GOBO1ROT7;
    private String GOBO1ROT8;
    private String GOBO1ROT9;
    private String GOBO1ROT10;
    private String GOBO1ROT11;
    private String GOBO1ROT12;
    @Field("GOBO2_1(open)")
    private String GOBO2_1_open;
    @Field("GOBO2_2")
    private String GOBO2_2;
    @Field("GOBO2_3(1-5)")
    private String GOBO2_3;
    @Field("GOBO2_4(6-10)")
    private String GOBO2_4;
    @Field("GOBO2_5(11-15)")
    private String GOBO2_5;
    @Field("GOBO2_6(16-20)")
    private String GOBO2_6;
    @Field("GOBO2_7(21-25)")
    private String GOBO2_7;
    @Field("GOBO2_8(26-30)")
    private String GOBO2_8;
    @Field("GOBO2_9(31-35)")
    private String GOBO2_9;
    @Field("GOBO2_10(open)")
    private String GOBO2_10;
    @Field("GOBO2_11(36-40)")
    private String GOBO2_11;
    @Field("GOBO2_12(41-45)")
    private String GOBO2_12;
    @Field("GOBO2_13(46-50)")
    private String GOBO2_13;
    @Field("GOBO2_14(51-55)")
    private String GOBO2_14;
    @Field("GOBO2_15(56-60)")
    private String GOBO2_15;
    @Field("GOBO2_16(60-64)")
    private String GOBO2_16;
    @Field("GOBO2_17(open)")
    private String GOBO2_17;
    @Field("GOBO2_18(65-69)")
    private String GOBO2_18;
    @Field("GOBO2_19(70-74)")
    private String GOBO2_19;
    @Field("GOBO2_20(74-79)")
    private String GOBO2_20;

    public static final String[] FIELD_ORDER = {"id","ICID", "productSN", "PAN", "TIL", "DIMMER", "ZOOM", "FOCUS", "COLOR", "COLOR_MOVE",
                "CYAN", "MAGENTA", "YELLOW", "GOBO1", "OPEN", "GOBO1ROT1", "GOBO1ROT2", "GOBO1ROT3", "GOBO1ROT4", "GOBO1ROT5",
                "GOBO1ROT6", "GOBO1ROT7", "GOBO1ROT8", "GOBO1ROT9", "GOBO1ROT10", "GOBO1ROT11", "GOBO1ROT12", "GOBO2_1_open",
                "GOBO2_2","GOBO2_3","GOBO2_4","GOBO2_5","GOBO2_6","GOBO2_7","GOBO2_8", "GOBO2_9","GOBO2_10","GOBO2_11",
                "GOBO2_12","GOBO2_13","GOBO2_14","GOBO2_15","GOBO2_16","GOBO2_17","GOBO2_18","GOBO2_19","GOBO2_20"
    };
}
