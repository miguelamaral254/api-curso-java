package com.example.demo.services;

import com.example.demo.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());
    private List<Person> persons = new ArrayList<>();

    public List<Person> findAll() {
        logger.info("Finding all persons!");
        if (persons.isEmpty()) {
            for (int i = 1; i < 8; i++) {
                Person person = mockPerson(i);
                persons.add(person);
            }
        }
        return persons;
    }

    public Person findById(String id) {
        logger.info("Finding one person!");
        return persons.stream()
                .filter(person -> String.valueOf(person.getId()).equals(id))
                .findFirst()
                .orElse(null);
    }

    public Person createPerson(Person person) {
        logger.info("Creating new person!");
        person.setId(counter.incrementAndGet());
        persons.add(person);
        return person;
    }

    public Person updatePerson(String id, Person updatedPerson) {
        logger.info("Updating person with ID " + id);
        for (Person person : persons) {
            if (String.valueOf(person.getId()).equals(id)) {
                person.setFirstName(updatedPerson.getFirstName());
                person.setLastName(updatedPerson.getLastName());
                person.setAddress(updatedPerson.getAddress());
                person.setGender(updatedPerson.getGender());
                person.setAge(updatedPerson.getAge());
                return person;
            }
        }
        return null; // Or throw an exception if person not found
    }

    public void deletePerson(String id) {
        logger.info("Deleting a person!");
        persons.removeIf(p -> String.valueOf(p.getId()).equals(id));
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Person LastName " + i);
        person.setAddress("Person " + i + " Address");
        person.setGender("Person " + i + " gender");
        person.setAge(26 + i);
        return person;
    }
}
