package org.example.LEB.service.serviceImpl;

import org.example.LEB.entity.deviceEntity.CobraMk2Entity;
import org.example.LEB.repository.deviceRepository.CobraMk2Repository;
import org.example.LEB.service.deviceService.CobraMk2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CobraMk2ServiceImpl extends BaseServiceImpl<CobraMk2Entity> implements CobraMk2Service {

    @Autowired
    public CobraMk2ServiceImpl(@Qualifier("cobraMk2Repository") CobraMk2Repository cobraMk2Repository) {
        super(cobraMk2Repository);
    }
}