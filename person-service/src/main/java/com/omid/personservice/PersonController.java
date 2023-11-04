package com.omid.personservice;

import org.hibernate.event.internal.DefaultPersistOnFlushEventListener;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAll(){
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable("id") Integer id){
        return personService.getPersonById(id);
    }

    @PostMapping
    public Person create(@RequestBody Person person){
        return personService.createPerson(person);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable("id") Integer id,@RequestBody Person person){
        return personService.updatePerson(id, person);
    }

    @GetMapping("/search")
    public Page<Person> search(PersonSearchFilter personSearchFilter, Pageable pageable){
        return personService.searchPerson(personSearchFilter,pageable);
    }

}
