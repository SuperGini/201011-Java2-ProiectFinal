package server.dao.impl.autovehicle;

import server.dao.PartDao;
import server.model.autovehicle.Part;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

public class PartDaoImpl implements PartDao {

    private EntityManager entityManager;

    public PartDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean createPart(Part part){
        entityManager.getTransaction().begin();
        entityManager.persist(part);
        entityManager.getTransaction().commit();

        return entityManager.getTransaction().getRollbackOnly();

    }

    @Override
    public Part findPartById(int id){
       return entityManager.find(Part.class, id);
    }

    @Override
    public Optional<Part> findPartByName(String partName){

       TypedQuery<Part> query = entityManager.createNamedQuery("Part.findByName",Part.class);
       query.setParameter("partName", partName);
       return query.getResultStream().findFirst();
    }

    @Override
    public int increasePartCount(int count, String partName){
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("UPDATE Part p SET p.count = p.count + :increment WHERE p.partName = : partName");
        query.setParameter("increment", count);
        query.setParameter("partName",partName);
        int rows = query.executeUpdate();

        entityManager.getTransaction().commit();

        return rows;
    }

    @Override
    public Collection<Part> findAllParts(){
       TypedQuery<Part> query = entityManager.createNamedQuery("Part.findAll", Part.class);

        return query.getResultList();

    }

}
