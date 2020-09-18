package server.dao.impl.client;

import server.dao.PersonDao;
import server.model.client.Person;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {

    private EntityManager entityManager;

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
    public Optional<Person> findPersonByName(String name){
        TypedQuery<Person> query = entityManager.createNamedQuery("Person.findByName",Person.class);

        query.setParameter("name", name);

        return query.getResultStream().findFirst();

    }

}
