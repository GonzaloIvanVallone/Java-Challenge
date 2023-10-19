package Meyguer.ChromaticLantern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ChromaticLanternApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChromaticLanternApplication.class, args);
	}

}
