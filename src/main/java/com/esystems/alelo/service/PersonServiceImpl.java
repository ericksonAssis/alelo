package com.esystems.alelo.service;

import com.esystems.alelo.entity.Person;
import com.esystems.alelo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> getPersonById(long id) {
        return personRepository.findById(id);
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void removePerson(Person person) {
        personRepository.delete(person);
    }
}
