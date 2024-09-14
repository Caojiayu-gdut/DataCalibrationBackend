package org.example.LEB.service.serviceImpl;

import org.example.LEB.entity.deviceEntity.KyakamiEntity;
import org.example.LEB.repository.deviceRepository.KyakamiRepository;
import org.example.LEB.service.deviceService.KyakamiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class KyakamiServiceImpl extends BaseServiceImpl<KyakamiEntity> implements KyakamiService {

    @Autowired
    public KyakamiServiceImpl(@Qualifier("kyakamiRepository") KyakamiRepository kyakamiRepository) {
        super(kyakamiRepository);
    }
}
