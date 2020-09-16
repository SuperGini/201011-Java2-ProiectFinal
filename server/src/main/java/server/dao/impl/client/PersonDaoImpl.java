package server.dao.impl.client;

import server.dao.PersonDao;
import server.model.client.Person;

import javax.persistence.EntityManager;

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
}
