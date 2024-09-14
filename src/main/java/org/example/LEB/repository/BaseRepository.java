package org.example.LEB.repository;

import org.example.LEB.entity.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaseRepository<T extends BaseEntity, ID> extends MongoRepository<T, ID> {
    Optional<T> findByProductSN(String productSN);

    List<T> findByProductSNIn(List<String> productSNs);
    Optional<T> findByICID(String ICID);
    T deleteByProductSN(String produceSN);
    T deleteByICID(String ICID);
}
