package org.example.LEB.controller.deviceController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.LEB.controller.BaseController;
import org.example.LEB.entity.deviceEntity.CobraMk2Entity;
import org.example.LEB.service.deviceService.CobraMk2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cobraMk2")
public class CobraMk2Controller extends BaseController<CobraMk2Entity> {

    @Autowired
    public CobraMk2Controller(CobraMk2Service cobraMk2Service, ObjectMapper objectMapper) {
        super(cobraMk2Service, objectMapper);
    }
}