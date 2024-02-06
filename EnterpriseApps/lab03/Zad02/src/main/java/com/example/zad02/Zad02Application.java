package com.example.zad02;

import com.example.zad02.domain.Person;
import com.example.zad02.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class Zad02Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext =  SpringApplication.run(Zad02Application.class, args);

		PersonService service = applicationContext.getBean(PersonService.class);

		Person prezes = service.getPrezes();
		Person wiceprezes = service.getWiceprezes();
		Person sekretarz = service.getSekretarz();

		System.out.println(prezes);
		System.out.println(wiceprezes);
		System.out.println(sekretarz);

		Person manager = applicationContext.getBean("manager", Person.class);
		Person pracownik = applicationContext.getBean("pracownik", Person.class);

		System.out.println(manager);
		System.out.println(pracownik);

		List<Person> people = service.getPeople();
		for (Person person : people) {
			System.out.println(person);
		}
	}
}
