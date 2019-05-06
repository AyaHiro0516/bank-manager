package cn.ayahiro.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.ayahiro.manager.mapper")
@SpringBootApplication
public class BankManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankManagerApplication.class, args);
	}

}
