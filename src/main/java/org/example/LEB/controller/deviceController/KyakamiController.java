package org.example.LEB.controller.deviceController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.LEB.controller.BaseController;
import org.example.LEB.entity.deviceEntity.KyakamiEntity;
import org.example.LEB.service.deviceService.KyakamiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kyakami")
public class KyakamiController extends BaseController<KyakamiEntity> {

    @Autowired
    public KyakamiController(KyakamiService kyakamiService, ObjectMapper objectMapper) {
        super(kyakamiService, objectMapper);
    }
}