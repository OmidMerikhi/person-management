package com.omid.personservice;

import com.querydsl.core.BooleanBuilder;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    private static final QPerson qPerson=QPerson.person;

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Person getPersonById(Integer id){
        return personRepository.getPersonById(id);
    }

    public Person createPerson(Person person){
        return personRepository.save(person);
    }

    public Person updatePerson(Integer id,Person person){
        Person dbPerson=getPersonById(id);
        if(person.getFirstName()!=null){
            dbPerson.setFirstName(person.getFirstName());
        }
        if(person.getLastName()!=null){
            dbPerson.setLastName(person.getLastName());
        }
        if(person.getCity()!=null){
            dbPerson.setCity(person.getCity());
        }

        return personRepository.saveAndFlush(dbPerson);

    }

    public Page<Person> searchPerson(PersonSearchFilter personSearchFilter, Pageable pageable){
        BooleanBuilder predicate=new BooleanBuilder();
        if(personSearchFilter.getFistName()!=null){
            predicate.and(qPerson.firstName.contains(personSearchFilter.getFistName()));
        }
        if(personSearchFilter.getLastName()!=null){
            predicate.and(qPerson.lastName.contains(personSearchFilter.getLastName()));
        }
        if(personSearchFilter.getCity()!=null){
            predicate.and(qPerson.city.contains(personSearchFilter.getCity()));
        }

        return personRepository.findAll(predicate,pageable);

    }
}
