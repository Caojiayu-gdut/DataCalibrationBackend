package org.example.LEB.entity.deviceEntity;

import lombok.Data;
import org.example.LEB.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
@Document("RIVALE_PROFILE_S")
public class RivaleProfileSEntity extends BaseEntity {

    @Id
    private String PAN;
    private String TITL;
    private String DIMMER;
    private String ZOOM;
    private String FOCUS;
    private String COLOR;
    private String CYAN;
    private String MAGENTA;
    private String YELLOW;
    private String CTO;
    private String CTY;
    private String GOBO1;
    private String GOBO1ROT;
    private String GOBO2ROT;
    private String GOBO3ROT;
    private String GOBO4ROT;
    private String GOBO5ROT;
    private String GOBO6ROT;
    private String GOBO7ROT;
    private String GOBO2;
    private String ANIMATION;
    private String IRIS;
}
