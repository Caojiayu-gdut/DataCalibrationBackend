package org.example.LEB.entity.deviceEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.LEB.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "calibration_data")
public class CalibrationDataEntity extends BaseEntity {

    @JsonProperty("productName")
    @Field("Product Name")
    private String productName;

    @JsonProperty("calibrationVersion")
    @Field("Calibration Version")
    private String calibrationVersion;

    @JsonProperty("lightSourceSN")
    @Field("Lightsource SN")
    private String lightSource;

    @JsonProperty("lensSN")
    @Field("Lens SN")
    private String lens;

}

