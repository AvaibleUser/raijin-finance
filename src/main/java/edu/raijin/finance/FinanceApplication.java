package edu.raijin.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "edu.raijin")
@ConfigurationPropertiesScan(basePackages = "edu.raijin")
@PropertySource(value = "file:${user.dir}/.env", ignoreResourceNotFound = true)
public class FinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}

}
