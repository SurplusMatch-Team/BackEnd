package tr.edu.agu.cs.surplus_match;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
		//(exclude = {DataSourceAutoConfiguration.class})
public class SurplusMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurplusMatchApplication.class, args);
	}

}