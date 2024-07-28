package com.example.demo.services;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());
    private List<Person> persons = new ArrayList<>();
    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Finding all persons!");
        return repository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one person!");
        return repository.findById(id).orElseThrow();
    }

    public Person createPerson(Person person) {
        logger.info("Creating new person!");
        return repository.save(person);
    }

    public Person updatePerson(Person person) {
        logger.info("Updating person with ID ");
        Person entity = repository.findById(person.getId()).orElseThrow();
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setAge(person.getAge());
        entity.setGender(person.getGender());
        return repository.save(person);
    }

    public void deletePerson(Long id) {
        logger.info("Deleting a person!");
        var entity = repository.findById(id).orElseThrow();
        repository.delete(entity);

    }


}
