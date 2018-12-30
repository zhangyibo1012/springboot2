package cn.zyblogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ProSpringboot2Application {

    public static void main(String[] args) {
        SpringApplication.run(ProSpringboot2Application.class, args);
    }

}

