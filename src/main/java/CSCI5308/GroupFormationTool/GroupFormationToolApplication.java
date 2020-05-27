package CSCI5308.GroupFormationTool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GroupFormationToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupFormationToolApplication.class, args);
		System.out.print("HELLO");
	}

}
