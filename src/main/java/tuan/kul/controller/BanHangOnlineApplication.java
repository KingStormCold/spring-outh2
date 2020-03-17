package tuan.kul.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(value = "tuan.kul")
@EnableJpaRepositories(value = "tuan.kul.dao")
@EntityScan(basePackages = "tuan.kul")
@EnableTransactionManagement
public class BanHangOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanHangOnlineApplication.class, args);
    }

}
