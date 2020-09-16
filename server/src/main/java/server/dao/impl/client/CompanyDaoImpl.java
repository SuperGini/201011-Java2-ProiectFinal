package server.dao.impl.client;

import server.dao.CompanyDao;
import server.model.client.Company;

import javax.persistence.EntityManager;

public class CompanyDaoImpl implements CompanyDao {

    private EntityManager entityManager;

    public CompanyDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean createCompany(Company company){
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();

        return entityManager.getTransaction().getRollbackOnly();

    }

    @Override
    public Company findCompanyById(int id){
        return entityManager.find(Company.class, id);
    }



}
