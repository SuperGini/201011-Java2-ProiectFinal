package server.service.client;

import lib.dto.client.PersonDto;
import server.convert.autovehicle.VehicleConvetor;
import server.convert.client.PersonConvertor;
import server.dao.PersonDao;
import server.dao.impl.client.PersonDaoImpl;
import server.model.autovehicle.Vehicle;
import server.model.client.Person;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonServiceImpl extends UnicastRemoteObject implements lib.service.PersonService {

    private PersonDao personDao;


    public PersonServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        personDao = new PersonDaoImpl(entityManager);

    }

    @Override
    public boolean createPerson(PersonDto personDto){

        Person person = PersonConvertor.convert(personDto);

        Set<Vehicle> vehicles = personDto.getVehicleDtos().stream()
                                        .map(VehicleConvetor::convert)
                                        .collect(Collectors.toSet());
        person.setVehicles(vehicles);
        vehicles.stream().forEach(s -> s.setClient(person));

        Optional<Person> optionalPerson = personDao.findPersonByName(person.getName());

         if(optionalPerson.isEmpty()){
            return personDao.createPerson(person);
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
