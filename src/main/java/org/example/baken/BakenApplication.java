package org.example.baken;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("org.example.baken.mapper")// 指定 Mapper 接口的包路径
public class BakenApplication {
	public static void main(String[] args) {
		SpringApplication.run(BakenApplication.class, args);
	}

}
