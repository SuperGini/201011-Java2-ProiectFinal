package lib.service;

import lib.dto.client.PersonDto;

public interface PersonService {
    boolean createPerson(PersonDto personDto);

    PersonDto findPersonById(int id);

    PersonDto findPersonByName(String name);
}
