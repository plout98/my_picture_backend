package com.plout.mypicture;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.plout.mypicture.mappper")
public class MyPictureApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPictureApplication.class, args);
    }

}
