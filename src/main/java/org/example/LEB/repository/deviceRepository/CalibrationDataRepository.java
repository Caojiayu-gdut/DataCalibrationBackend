package org.example.LEB.repository.deviceRepository;

import org.example.LEB.entity.deviceEntity.CalibrationDataEntity;
import org.example.LEB.entity.deviceEntity.CobraMk2Entity;
import org.example.LEB.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalibrationDataRepository extends BaseRepository<CalibrationDataEntity, String> {
    // 你可以在这里定义额外的查询方法，例如：
    // List<CalibrationDataEntity> findBySensorType(String sensorType);
    Optional<CalibrationDataEntity> findByProductSN(String productSN);

    List<CalibrationDataEntity> findByProductSNIn(List<String> productSNs);  // 根据 Product SN 查找产品
    Optional<CalibrationDataEntity> findByICID(String ICID);  // 根据 ICID 查找产品
}