package org.example.LEB.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.LEB.entity.BaseEntity;
import org.example.LEB.exceptionHandler.DuplicateEntityException;
import org.example.LEB.exceptionHandler.EntityExistsException;
import org.example.LEB.exceptionHandler.EntityNotFoundException;
import org.example.LEB.exceptionHandler.UnknownException;
import org.example.LEB.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

//@Component
@Slf4j
public  abstract class BaseController<T extends BaseEntity> {
    protected final BaseService<T> service;
    protected final ObjectMapper objectMapper;

    public BaseController(BaseService<T> service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<List<T>> getAll() {
        log.info("Request-LIST: List all params of device.");
        try {
            List<T> result = service.findAll();
            return ResponseEntity.ok().body(result);
        }catch(EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/ICID/{ICID}")
    public ResponseEntity<Optional<T>> getByICID(@PathVariable String ICID) {
        log.info("Request-GETICID: Get the device with ICID: {}.", ICID);
        Optional<T> result = service.findByICID(ICID);
        // Check if the productSN already exists
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/productSN/{productSN}")
    public ResponseEntity<Object> getBySN(@PathVariable String productSN) {
        log.info("Request-GETProductSN：Get the device with productSN: {}.", productSN);
        try {
            Optional<T> result = service.findBySN(productSN);
            return ResponseEntity.ok(result);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (DuplicateEntityException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (UnknownException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/productSNs/{productSNs}")
    public ResponseEntity<Object> getListBySN(@RequestParam List<String> productSNs){
        log.info("Request-GETProductSNs：Get the devices with productSNs: {}.", productSNs);
        try{
            List<T> result = service.findListBySN(productSNs);
            return ResponseEntity.ok(result);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (DuplicateEntityException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (UnknownException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<T> save(@RequestBody T entity) {
        log.info("Request-Insert: Create a new device: {}.", entity);
        try{
            T result = service.save(entity);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{productSN}").buildAndExpand(result.getProductSN()).toUri();
            return ResponseEntity.created(location).body(result);
        }catch (EntityExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(entity);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<T> update( @RequestBody T entity) {
        String productSN = entity.getProductSN();
        log.info("Request-UPDATE: Update the device with productSN: {}.", productSN);
        try{
            T result =service.update(entity);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/ICID/{ICID}")
    public ResponseEntity<?> deleteByICID(@PathVariable String ICID) {
        log.info("Request-DELETEICID: Delete the device with ICID: {}.", ICID);
        try{
           T result = service.deleteByICID(ICID);
           return ResponseEntity.ok(result);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/productSN/{productSN}")
    public ResponseEntity<?> deleteBySN(@PathVariable String productSN) {
        log.info("Request-DELETEProductSN: Delete device by productSN:: {}", productSN);
        try{
            T result = service.deleteBySN(productSN);
            return ResponseEntity.ok(result);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (UnknownException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/insertList")
    public ResponseEntity<String> saveList(@RequestBody List<T> entities) {
        log.info("Request-insertList: Create new devices: {}.", entities);
        try {
            List<T> result = service.saveList(entities);
            return ResponseEntity.status(HttpStatus.CREATED).body(result.toString());
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Valid entities not found.");
        } catch (UnknownException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error occurred while saving devices.");
        }
    }
}
