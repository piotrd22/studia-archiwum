package com.example.zad01.service;

import com.example.zad01.domain.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersonService {
    public Map<String, Person> readCsvFile(String filePath) {
        Map<String, Person> personMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                String id = record.get("id");
                String name = record.get("name");
                String surname = record.get("surname");
                String email = record.get("email");
                int dateOfBirth = Integer.parseInt(record.get("date_of_birth"));

                Person person = new Person(id, name, surname, email, dateOfBirth);
                personMap.put(id, person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return personMap;
    }
}
