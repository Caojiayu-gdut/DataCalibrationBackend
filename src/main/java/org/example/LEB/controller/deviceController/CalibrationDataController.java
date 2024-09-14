package org.example.LEB.controller.deviceController;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.LEB.controller.BaseController;
import org.example.LEB.entity.deviceEntity.CalibrationDataEntity;
import org.example.LEB.service.deviceService.CalibrationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/data")
public class CalibrationDataController extends BaseController<CalibrationDataEntity> {

//    @Autowired
//    private CalibrationDataServiceImpl calibrationDataServiceImpl;

//    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public CalibrationDataController(CalibrationDataService calibrationDataService, ObjectMapper objectMapper) {
        super(calibrationDataService, objectMapper);
    }

//    @PostMapping("/insert")
//    public ResponseEntity<CalibrationDataEntity> create(@RequestBody String json) {
//        try {
//            CalibrationDataEntity calibrationDataEntity = objectMapper.readValue(json, CalibrationDataEntity.class);
//            CalibrationDataEntity savedEntity = calibrationDataServiceImpl.insert(calibrationDataEntity);
//            return ResponseEntity.ok(savedEntity);
//        } catch (IOException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @GetMapping("/productSN/{productSN}")
//    public ResponseEntity<Object> getByProductSN(@PathVariable String productSN) {
//        log.info("Request-GETProductSN：Get the device with productSN: {}.", productSN);
//        try {
//            Optional<CalibrationDataEntity> result = calibrationDataServiceImpl.findByProductSN(productSN);
//            if (result.isPresent()) {
//                return ResponseEntity.ok(result.get());
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        } catch (DuplicateEntityException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        } catch (UnknownException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CalibrationDataEntity>> getAll() {
//        List<CalibrationDataEntity> entities = calibrationDataServiceImpl.findAll();
//        return ResponseEntity.ok(entities);
//    }
//
//    @DeleteMapping("productSN/{productSN}")
//    public ResponseEntity<Void> delete(@PathVariable String productSN) {
//        calibrationDataServiceImpl.deleteByProductSN(productSN);
//        return ResponseEntity.noContent().build();
//    }
}