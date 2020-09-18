package server.dao;

import server.model.client.Person;

import java.util.Optional;

public interface PersonDao {

    boolean createPerson(Person person);

    Person findPersonById(int id);


    Optional<Person> findPersonByName(String name);
}
