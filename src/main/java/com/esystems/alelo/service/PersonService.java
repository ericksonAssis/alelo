package com.esystems.alelo.service;

import com.esystems.alelo.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public List<Person> getAllPerson();
    public Optional<Person> getPersonById(long id);
    public Person savePerson(Person person);
    public void removePerson(Person person);

}
