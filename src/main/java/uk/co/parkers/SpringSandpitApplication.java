package uk.co.parkers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringSandpitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSandpitApplication.class, args);
	}
}
