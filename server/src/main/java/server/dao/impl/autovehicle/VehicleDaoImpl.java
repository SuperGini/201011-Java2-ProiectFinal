package server.dao.impl.autovehicle;

import server.dao.VehicleDao;
import server.model.autovehicle.Vehicle;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;

public class VehicleDaoImpl implements VehicleDao {

    private EntityManager entityManager;

    public VehicleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean createVehicle(Vehicle vehicle){
        entityManager.getTransaction().begin();
        entityManager.persist(vehicle);
        entityManager.getTransaction().commit();
        return entityManager.getTransaction().getRollbackOnly();
    }

    @Override
    public Vehicle findById(int id){
        return entityManager.find(Vehicle.class, id);
    }

    @Override
    public Optional<Vehicle> findBySerialNumber(String serialNumber){
        String sql ="SELECT v FROM Vehicle v  WHERE v.serialNumber = :serialNumber";

        var query = entityManager.createQuery(sql, Vehicle.class);
        query.setParameter("serialNumber", serialNumber);

        return query.getResultStream().findFirst();
    }

    @Override
    public Collection<Vehicle> findAllVehicles(){
        String sql = "SELECT v FROM Vehicle v";

        var query = entityManager.createQuery(sql, Vehicle.class);

        return query.getResultList();
    }
}
