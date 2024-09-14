package org.example.LEB.service.serviceImpl;

import org.example.LEB.entity.deviceEntity.CalibrationDataEntity;
import org.example.LEB.repository.deviceRepository.CalibrationDataRepository;
import org.example.LEB.service.deviceService.CalibrationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CalibrationDataServiceImpl extends BaseServiceImpl<CalibrationDataEntity> implements CalibrationDataService {
    @Autowired
    public CalibrationDataServiceImpl(@Qualifier("calibrationDataRepository")CalibrationDataRepository calibrationDataRepository){
        super(calibrationDataRepository);
    }
//    private CalibrationDataRepository calibrationDataRepository;
//
//    public CalibrationDataServiceImpl(BaseRepository<CalibrationDataEntity, String> repository) {
//        super(repository);
//    }

    // 如有更多特定于CalibrationDataEntity的服务方法，可以在这里添加
}