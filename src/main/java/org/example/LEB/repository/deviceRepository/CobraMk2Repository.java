package org.example.LEB.repository.deviceRepository;

import org.example.LEB.entity.deviceEntity.CobraMk2Entity;
import org.example.LEB.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
//COBRA_MK2接口
public interface CobraMk2Repository extends BaseRepository<CobraMk2Entity, String> {
    Optional<CobraMk2Entity> findByProductSN(String productSN);
    List<CobraMk2Entity> findByProductSNIn(List<String> productSNs);  // 根据 Product SN 查找产品
    Optional<CobraMk2Entity> findByICID(String ICID);  // 根据 ICID 查找产品

}
