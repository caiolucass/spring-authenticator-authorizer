package io.getarrays.userservice;

import io.getarrays.userservice.model.Role;
import io.getarrays.userservice.model.User;
import io.getarrays.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * @author Caio Lucas (https://github.com/caiolucass)
 * @version 1.0
 * @since 02/03/2022
 *
 */


@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	/**
	 *
	 * @return
	 * gera uma nova cripitografa das informacoes do usuario
	 */
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/**
	 *
	 * @param userService
	 * @return
	 */
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			//adicionando Role
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			//adicionando usuario
			userService.saveUser(new User(null, "Caio Lucas", "Cafox", "Coxinha@123", new ArrayList<>()));
			userService.saveUser(new User(null, "Joao Reis", "JhonKings", "Jo@730630", new ArrayList<>()));
			userService.saveUser(new User(null, "Marcia Cristina", "Macinha", "Bita@1936", new ArrayList<>()));
			userService.saveUser(new User(null, "Luiz Fernando", "Pitoniel", "Luiz@Zero123456", new ArrayList<>()));

			//adicionando Role ao usuario
			userService.addRoleToUser("Cafox", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("Macinha", "ROLE_ADMIN");
			userService.addRoleToUser("JhonKings", "ROLE_MANAGER");
			userService.addRoleToUser("Pitoniel", "ROLE_USER");
			userService.addRoleToUser("Pitoniel", "ROLE_ADMIN");
			userService.addRoleToUser("Pitoniel", "ROLE_MANAGER");
		};
	}
}
