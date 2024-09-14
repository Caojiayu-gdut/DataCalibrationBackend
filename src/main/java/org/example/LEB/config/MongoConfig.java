package org.example.LEB.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {
    // 可以在这里定义其他与MongoDB相关的配置
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create("mongodb://127.0.0.1:27017/LightingEquipmentDatabases_2024"), "LightingEquipmentDatabases_2024");
    }
}
