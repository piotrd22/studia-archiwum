package com.example.zad01;

import com.example.zad01.domain.Person;
import com.example.zad01.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class Zad01Application {

	public static void main(String[] args) {
		SpringApplication.run(Zad01Application.class, args);

		String csvFilePath = "db.csv";

		try {
			ClassPathResource resource = new ClassPathResource(csvFilePath);
			String absolutePath = resource.getFile().getAbsolutePath();

			PersonService personService = new PersonService();
			Map<String, Person> personMap = personService.readCsvFile(absolutePath);

			for (Person person : personMap.values()) {
				System.out.println(person);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
