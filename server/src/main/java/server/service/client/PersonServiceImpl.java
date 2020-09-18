package server.service.client;

import lib.dto.client.PersonDto;
import server.convert.client.PersonConvertor;
import server.dao.PersonDao;
import server.dao.impl.client.PersonDaoImpl;
import server.model.client.Person;

import javax.persistence.Persistence;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PersonServiceImpl implements lib.service.PersonService {

    private PersonDao personDao;

    public PersonServiceImpl() {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        personDao = new PersonDaoImpl(entityManager);
    }

    @Override
    public boolean createPerson(PersonDto personDto){
        Person person = PersonConvertor.convert(personDto);

        Optional<Person> optionalPerson = personDao.findPersonByName(person.getName());

         if(optionalPerson.isEmpty()){
             personDao.createPerson(person);
         }

         throw new IllegalArgumentException();
    }

    @Override
    public PersonDto findPersonById(int id){
        return PersonConvertor.convert(personDao.findPersonById(id));
    }

    @Override
    public PersonDto findPersonByName(String name){

       return personDao.findPersonByName(name)
                .map(PersonConvertor::convert)
                .orElseThrow(NoSuchElementException::new);

    }

}
