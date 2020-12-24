package cn.javabb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan("cn.javabb.*.mapper")
@SpringBootApplication
public class JavabbSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavabbSecurityApplication.class, args);
    }

}