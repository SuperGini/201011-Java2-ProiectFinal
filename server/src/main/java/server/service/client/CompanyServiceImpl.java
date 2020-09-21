package server.service.client;

import lib.dto.client.CompanyDto;
import server.convert.client.CompanyConvertor;
import server.dao.CompanyDao;
import server.dao.impl.client.CompanyDaoImpl;
import server.model.client.Company;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CompanyServiceImpl extends UnicastRemoteObject implements lib.service.CompanyService {

    private CompanyDao companyDao;

    public CompanyServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        companyDao = new CompanyDaoImpl(entityManager);
    }

    @Override
    public boolean ceateCompany(CompanyDto companyDto){
        Company company = CompanyConvertor.convert(companyDto);

        Optional<Company> optionalCompany = companyDao.findCompanyByName(company.getName());

        if(optionalCompany.isEmpty()){
           return companyDao.createCompany(company);
        }

        throw new IllegalArgumentException();
    }

    @Override
    public CompanyDto findById(int id){
       return CompanyConvertor.convert(companyDao.findCompanyById(id));
    }

    @Override
    public CompanyDto findCompanyByName(String name){

        return companyDao.findCompanyByName(name)
                .map(CompanyConvertor::convert)
                .orElseThrow(NoSuchElementException::new);
    }
}
