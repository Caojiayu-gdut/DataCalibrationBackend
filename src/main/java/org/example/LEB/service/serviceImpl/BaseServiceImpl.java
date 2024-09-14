package org.example.LEB.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.LEB.entity.BaseEntity;
import org.example.LEB.exceptionHandler.EntityExistsException;
import org.example.LEB.exceptionHandler.EntityNotFoundException;
import org.example.LEB.repository.BaseRepository;
import org.example.LEB.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public  abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected final BaseRepository<T, String> repository;

    @Autowired
    public BaseServiceImpl(BaseRepository<T, String> repository) {
        this.repository = repository;
    }
    protected MongoTemplate mongoTemplate;

    // 1
    @Override
    public List<T> findAll() {
        //Check if the List is empty.
        List<T> existing = repository.findAll();
        if (existing.isEmpty()) {
            throw new EntityNotFoundException("Response-Error-List: No entity is found.");
        }else {
            log.info("Response-List：Found {} entities", existing.size());
            return existing;
        }
    }

    @Override
    public Optional<T> findByICID(String ICID) {
        //Check if the ICID is existed.
        Optional<T> existing = repository.findByICID(ICID);
        if (existing.isPresent()) {
            log.info("Response-GETICID：The device with ICID: {} is found successfully.", ICID);
            return existing;
        }else {
            throw new EntityNotFoundException("Response-Error-GETICID: No entity is found with ICID:"+ICID);
        }
    }

    @Override
    public Optional<T> findBySN(String productSN) {
        //Check if the ICID is existed.
        Optional<T> existing = repository.findByProductSN(productSN);
        if (existing.isPresent()) {
            log.info("Response-findByProductSN：The device with Product SN: {} is found successfully.", productSN);
            return existing;
        }else {
            throw new EntityNotFoundException("Response-Error-GETICID: No entity is found with ICID:" + productSN);
        }
    }

    @Override
    public List<T> findListBySN(List<String> productSNs) {
        //Check if the productSN is existed.
        if(productSNs==null||productSNs.isEmpty()){
            throw new EntityNotFoundException("The list of productSNs must not be null or empty.");
        }
        //One for collecting successful device,One for nonexistent device, the other for collecting already existed device.
        List<T> result = new ArrayList<>();
        List<String> noneExisted = new ArrayList<>();
        List<String> alreadyExisted = new ArrayList<>();

        //Check every productSNs in the list.
        for(String productSN:productSNs){
            List<T> existing = repository.findByProductSNIn(Collections.singletonList(productSN));
            if (existing.isEmpty()) {
                //None existed,add to the noneExisted
                noneExisted.add(productSN);
            } else if (existing.size() > 1) {
                //Already existed,add to the alreadyExisted
                alreadyExisted.add(productSN);
            } else {
                log.info("Response-GETProductSN：The device with productSNs: {} is found successfully.", productSN);
                result.addAll(existing);
            }
        }
        if(!noneExisted.isEmpty()||!alreadyExisted.isEmpty()){
            throw new EntityNotFoundException("");
        }
        return result;
    }

    @Override
    public T save(T entity) {
        //Check if the productSN already exists.
        Optional<T> existing = repository.findByProductSN(entity.getProductSN());
        if (existing.isPresent()) {
            throw new EntityExistsException("Response-Error-Insert:The entity is already existed with same product SN."+entity.getProductSN());
        }else {
            entity.setCreatedAt(LocalDateTime.now());
            entity.setUpdatedAt(LocalDateTime.now());
            log.info("Response-Insert：The device with productSN: {} is saved successfully.", entity.getProductSN());
            return repository.save(entity);
        }
    }

    @Override
    public List<T> saveList(List<T> entities) {
//        Check if the productSN is existed.
            if (entities == null || entities.isEmpty()) {
                throw new EntityNotFoundException("The list of productSNs must not be null or empty.");
            }
            //One for collecting successful result,the other for collecting already existed result.
            List<T> result = new ArrayList<>();
            List<String> alreadyExisted = new ArrayList<>();

            //Check every productSNs in the list.
            for (T entity : entities) {
                List<T>existing = repository.findByProductSNIn(Collections.singletonList(entity.getProductSN()));
                if(!existing.isEmpty()){
                    //Already existed,add to the alreadyExisted
                    alreadyExisted.add(entity.getProductSN());
                }else{
                    //Update and add to the result
                    entity.setCreatedAt(LocalDateTime.now());
                    entity.setUpdatedAt(LocalDateTime.now());
                    T savedEntity = repository.save(entity);
                    result.add(savedEntity);
                    log.info("Response-InsertList：The device with productSNs: {} are saved successfully.", entity.getProductSN());
                }
            }
            //Info the message if same productSN is existed
            if(!alreadyExisted.isEmpty()){
                throw new EntityExistsException("Response-Error-Insert: The following entities are already existed with same product SNs: "
                        + alreadyExisted.stream().collect(Collectors.joining(", ")));
            }
            return result;
        }
    @Transactional
    public T update(T entity) {
        Optional<T> existingEntityOpt = repository.findByProductSN(entity.getProductSN()).stream().findFirst();

        if (existingEntityOpt.isPresent()) {
            T existingT = existingEntityOpt.get();
            // 保留原有的 createdAt 字段，更新其他字段
            copyNonNullProperties(entity, existingT);
            // 更新 updatedAt 字段
            existingT.setUpdatedAt(LocalDateTime.now());
            existingT.setICID(entity.getICID());
            existingT.setVariableParams(entity.getVariableParams());

//          获取并记录主键值
//          Object primaryKeyValue = getPrimaryKeyValue(existingT);
//          log.info("Response-Update: The device with productSN: {} is updated successfully. Primary key: {}", entity.getProductSN(), primaryKeyValue);

            return repository.save(existingT);
        } else {
            throw new EntityNotFoundException("Response-Error-Update: Entity is not found with productSN:{" + entity.getProductSN() + "}");
        }
    }

    private Object getPrimaryKeyValue(Object entity) {
        try {
            Class<?> clazz = entity.getClass();
            Field idField;

            try {
                idField = clazz.getDeclaredField("id");
                log.debug("Found field 'id' in class " + clazz.getName());
            } catch (NoSuchFieldException e) {
                log.debug("'id' field not found in class " + clazz.getName() + ", trying '_id'");
                idField = clazz.getDeclaredField("_id");
                log.debug("Found field '_id' in class " + clazz.getName());
            }

            idField.setAccessible(true);
            return idField.get(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Can't get primary key value from entity", e);
            throw new RuntimeException("Can't get primary key value from entity", e);
        }
    }

    @Override
    public T deleteByICID(String ICID) {
        Optional<T> existing = repository.findByICID(ICID);
        if (existing.isPresent()) {
            log.info("Response-DELETEICID：The device with ICID: {} is deleted successfully.", ICID);
            repository.delete(existing.get());
        }else {
            throw new EntityNotFoundException("Response-Error-DELETEICID: No entity is found with ICID:"+ICID);
        }
        return null;
    }

    @Override
    public T deleteBySN(String productSN) {
        Optional<T> existing = repository.findByProductSN(productSN).stream().findFirst();
        if (existing.isPresent()) {
            log.info("Response-DELETEProductSN：The device with productSN: {} is deleted successfully.", productSN);
            repository.delete(existing.get());
        }else {
            throw new EntityNotFoundException("Response-Error-DELETEProductSN: No entity is found with productSN:"+productSN);
        }
        return null;
    }

    //Copy method
    private void copyNonNullProperties(T source, T target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

//    @Override
//    public List<String> getFieldOrderFromDatabase(T entity){
//
//        String collectionName = mongoTemplate.getCollectionName(entity.getClass());
//        return mongoTemplate.getCollection(collectionName).find().first()
//                .entrySet()
//                .stream()
//                .map(entry -> entry.getKey())
//                .collect(Collectors.toList());
//    }
//    @Override
//    public T saveEntity(T entity) {
//
//        mongoTemplate.save(entity);
//        return entity;
//    }
}