package com.example.PathoGoneVaccinationBookingSystem.Repository;

import com.example.PathoGoneVaccinationBookingSystem.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByEmailId(String emailId);

    List<Person> findByAgeAndName(int age, String name);
}
