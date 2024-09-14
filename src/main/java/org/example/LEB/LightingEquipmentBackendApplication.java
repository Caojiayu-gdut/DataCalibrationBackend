package org.example.LEB;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.LEB.entity.deviceEntity.CobraMk2Entity;
import org.example.LEB.entity.deviceEntity.KyakamiEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//多个查找与创建已编写，未成功运行
@Slf4j
//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories(basePackages = "org.example.LEB.repository")
@EnableMongoAuditing // 启用审计功能
public class LightingEquipmentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LightingEquipmentBackendApplication.class, args);
        log.info("Lighting-Equipment-Backend Application IS RUNNING!");

        // 测试JSON序列化
//        testJsonSerialization();
    }

    private static void testJsonSerialization() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CobraMk2Entity cobraMk2Entity = new CobraMk2Entity();
            KyakamiEntity kyakamiEntity = new KyakamiEntity();

            String cobraMk2EntityJson  = objectMapper.writeValueAsString(cobraMk2Entity);
            String kyakamiEntityJson = objectMapper.writeValueAsString(kyakamiEntity);

            System.out.println("cobraMk2Entity: " + cobraMk2EntityJson);
            System.out.println("kyakamiEntity: " + kyakamiEntityJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
