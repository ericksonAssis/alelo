package com.esystems.alelo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import com.esystems.alelo.entity.Person;
import com.esystems.alelo.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceTest {


        @Mock
        private PersonRepository personRepository;

        @InjectMocks
        private PersonServiceImpl personService;

        @Before
        public void setup(){
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testGetAllPerson(){
            List<Person> personList = new ArrayList<>();
            personList.add(new Person(1L,"Person 1"));
            personList.add(new Person(2L,"Person 2"));
            personList.add(new Person(3L,"Person 3"));
            when(personRepository.findAll()).thenReturn(personList);

            List<Person> result = personService.getAllPerson();
            assertEquals(3, result.size());
        }

        @Test
        public void testGetPersonById(){
            Person person = new Person(1L,"Person 1");
            personRepository.save(person);
            when(personRepository.findById(1L).get()).thenReturn(person);
            Person result = personService.getPersonById(1).get();
            assertEquals(1, result.getId());
            assertEquals("Person  1", result.getName());
        }

        @Test
        public void savePerson(){
            Person person = new Person(8,"Person 8");
            when(personRepository.save(person)).thenReturn(person);
            Person result = personService.savePerson(person);
            assertEquals(8, result.getId());
            assertEquals("Person 8", result.getName());
        }

        @Test
        public void removePerson(){
            Person person = new Person(8,"Person 8");
            personService.removePerson(person);
            verify(personRepository, times(1)).delete(person);
        }



}
