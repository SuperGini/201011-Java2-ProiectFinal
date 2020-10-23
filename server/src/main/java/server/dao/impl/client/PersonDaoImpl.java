package server.dao.impl.client;

import server.dao.interfaces.PersonDao;
import server.model.client.Person;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {

    private final EntityManager entityManager;

    public PersonDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean createPerson(Person person){
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();

        return entityManager.getTransaction().getRollbackOnly();
    }

    @Override
    public Person findPersonById(int id){
       return  entityManager.find(Person.class, id);

    }

    @Override
    public Person mergePerson(Person person){
        return entityManager.merge(person);

    }

    @Override
    public Optional<Person> findPersonByName(String name){
        TypedQuery<Person> query = entityManager.createNamedQuery("Person.findByName",Person.class);

        query.setParameter("name", name);

        Optional<Person> person = query.getResultStream().findFirst();

        return person;
    }





}
