package com.scaryterry.ImageManagement;

import com.scaryterry.ImageManagement.FileUpload.StorageProperties;
import com.scaryterry.ImageManagement.FileUpload.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ImageManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageManagementApplication.class, args);


	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
