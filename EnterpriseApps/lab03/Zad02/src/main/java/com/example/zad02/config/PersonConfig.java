package com.example.zad02.config;

import com.example.zad02.domain.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;

@Configuration
public class PersonConfig {
    @Bean
    @Qualifier("prezes")
    public Person prezesPerson() {
        return new Person("12", "Prezes", "Kowalski", "kowalski@example.com", 1980);
    }

    @Bean
    @Qualifier("wiceprezes")
    public Person wiceprezesPerson() {
        return new Person("13", "Wiceprezes", "Nowak", "wice.nowak@example.com", 1975);
    }

    @Bean
    @Qualifier("sekretarz")
    public Person sekretarzPerson() {
        return new Person("14", "Sekretarz", "Dąbrowski", "sekretarz.dabrowski@example.com", 1990);
    }

    @Bean
    @Qualifier("people")
    public List<Person> personsFromCsv(@Value("${db.csv.file}") String csvFilePath) {
        ClassPathResource resource = new ClassPathResource(csvFilePath);

        List<Person> personList = new ArrayList<>();

        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                String id = record.get("id");
                String name = record.get("name");
                String surname = record.get("surname");
                String email = record.get("email");
                int dateOfBirth = Integer.parseInt(record.get("date_of_birth"));

                Person person = new Person(id, name, surname, email, dateOfBirth);
                personList.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return personList;
    }
}
