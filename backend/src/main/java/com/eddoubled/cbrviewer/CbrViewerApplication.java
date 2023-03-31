package com.eddoubled.cbrviewer;

import com.eddoubled.cbrviewer.service.Initializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CbrViewerApplication implements CommandLineRunner {

	Initializer initializer;

	public static void main(String[] args) {
		SpringApplication.run(CbrViewerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (args.length != 0 && "dbInit".equals(args[0])) {
			initializer.init();
		}
	}
}


