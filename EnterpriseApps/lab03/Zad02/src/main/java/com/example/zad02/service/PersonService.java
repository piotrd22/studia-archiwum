package com.example.zad02.service;

import com.example.zad02.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonService {

    private final Person prezes;
    private final Person wiceprezes;
    private final Person sekretarz;
    private final List<Person> people;

    public PersonService(
            @Qualifier("prezes") Person prezes,
            @Qualifier("wiceprezes") Person wiceprezes,
            @Qualifier("sekretarz") Person sekretarz,
            @Qualifier("people") List<Person> people
    ) {
        this.prezes = prezes;
        this.wiceprezes = wiceprezes;
        this.sekretarz = sekretarz;
        this.people = people;
    }

    public Person getPrezes() {
        return prezes;
    }

    public Person getWiceprezes() {
        return wiceprezes;
    }

    public Person getSekretarz() {
        return sekretarz;
    }

    public List<Person> getPeople() {
        return people;
    }
}
