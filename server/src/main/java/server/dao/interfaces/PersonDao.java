package server.dao.interfaces;

import server.model.client.Person;

import java.util.Optional;

public interface PersonDao {

    boolean createPerson(Person person);

    Person findPersonById(int id);


    Person mergePerson(Person person);

    Optional<Person> findPersonByName(String name);


}
