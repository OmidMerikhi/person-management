package com.omid.personservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue
    Integer id;
    String firstName;
    String lastName;
    String city;
}
