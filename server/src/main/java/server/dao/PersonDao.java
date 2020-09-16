package server.dao;

import server.model.client.Person;

public interface PersonDao {

    boolean createPerson(Person person);

    Person findPersonById(int id);


}
