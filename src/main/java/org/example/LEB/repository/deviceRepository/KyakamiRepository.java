package org.example.LEB.repository.deviceRepository;

import org.example.LEB.entity.deviceEntity.CobraMk2Entity;
import org.example.LEB.entity.deviceEntity.KyakamiEntity;
import org.example.LEB.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KyakamiRepository extends BaseRepository<KyakamiEntity, String> {
    Optional<KyakamiEntity> findByProductSN(String productSN);

    List<KyakamiEntity> findByProductSNIn(List<String> productSNs);  // 根据 Product SN 查找产品
    Optional<KyakamiEntity> findByICID(String ICID);  // 根据 ICID 查找产品

}
