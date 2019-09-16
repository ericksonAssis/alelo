package com.esystems.alelo.web;

import com.esystems.alelo.exception.PersonException;
import com.esystems.alelo.entity.Person;
import com.esystems.alelo.model.Response;
import com.esystems.alelo.service.PersonServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonServiceImpl personService;

    @ApiOperation("getAllPerson")
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllPerson() {
        logger.info("Returning all the Person´s");
        return new ResponseEntity<List<Person>>(personService.getAllPerson(), HttpStatus.OK);
    }

    @ApiOperation("getPersonById")
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) throws PersonException {
        logger.info("Person id to return " + id);
        Optional<Person> person = personService.getPersonById(id);
        if (person == null || person.get().getId() <= 0) {
            throw new PersonException("Person doesn´t exist");
        }
        return new ResponseEntity<Person>(personService.getPersonById(id).get(), HttpStatus.OK);
    }

    @ApiOperation("removePersonById")
    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removePersonById(@PathVariable("id") long id) throws PersonException {
        logger.info("Person id to remove " + id);
        Optional<Person> person = personService.getPersonById(id);
        if (person == null || person.get().getId() <= 0) {
            throw new PersonException("Person to delete doesn´t exist");
        }
        personService.removePerson(person.get());
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Person has been deleted"), HttpStatus.OK);
    }

    @ApiOperation("savePerson")
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public ResponseEntity<Person> savePerson(@RequestBody Person payload) throws PersonException {
        logger.info("Payload to save " + payload);

        return new ResponseEntity<Person>(personService.savePerson(payload), HttpStatus.OK);
    }

    @ApiOperation("updatePerson")
    @RequestMapping(value = "/person", method = RequestMethod.PATCH)
    public ResponseEntity<Person> updatePerson(@RequestBody Person payload) throws PersonException {
        logger.info("Payload to update " + payload);
        Optional<Person> person = personService.getPersonById(payload.getId());
        if (person == null || person.get().getId() <= 0) {
            throw new PersonException("Person to update doesn´t exist");
        }
        return new ResponseEntity<Person>(personService.savePerson(payload), HttpStatus.OK);
    }
}