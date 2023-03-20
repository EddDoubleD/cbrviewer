package com.eddoubled.cbrviewer;

import com.eddoubled.cbrviewer.model.ERole;
import com.eddoubled.cbrviewer.model.Role;
import com.eddoubled.cbrviewer.repository.RoleRepository;
import com.eddoubled.cbrviewer.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@SpringBootApplication
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CbrViewerApplication implements CommandLineRunner {

	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(CbrViewerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		roleRepository.deleteAll();
		roleRepository.save(new Role(ERole.ROLE_USER));
		roleRepository.save(new Role(ERole.ROLE_ADMIN));
		roleRepository.save(new Role(ERole.ROLE_MODERATOR));
	}
}


