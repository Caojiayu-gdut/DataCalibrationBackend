package org.example.LEB.service;

import org.example.LEB.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity>{
    // 查找所有实体
    List<T> findAll();
    // 查找实体通过ICID
    Optional<T> findByICID(String ICID);
    // 查找实体通过ProductSN
    Optional<T> findBySN(String productSN);
    // 查找多个实体通过ProductSN
    List<T> findListBySN(List<String> productSN);

//    T insert(T entity);
    // 保存实体通过productSN
    T save(T entity);

    List<T> saveList(List<T> entities);

    // 更新实体通过productSN
    T update(T entity);
    // 删除实体通过ICID
    T deleteByICID(String ICID);
    // 删除实体通过productSN
    T deleteBySN(String productSN);    // 添加这条删除方法

//    List<String> getFieldOrderFromDatabase(T entity);
//
//    T saveEntity(T entity);
}
