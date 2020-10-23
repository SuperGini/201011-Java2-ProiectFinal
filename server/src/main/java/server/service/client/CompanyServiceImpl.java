package server.service.client;

import lib.dto.client.CompanyDto;
import server.convert.autovehicle.VehicleConvetor;
import server.convert.client.CompanyConvertor;
import server.dao.interfaces.CompanyDao;
import server.dao.impl.client.CompanyDaoImpl;
import server.model.autovehicle.Vehicle;
import server.model.client.Company;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyServiceImpl extends UnicastRemoteObject implements lib.service.CompanyService {

    private final CompanyDao companyDao;

    public CompanyServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        companyDao = new CompanyDaoImpl(entityManager);
    }

    @Override
    public boolean ceateCompany(CompanyDto companyDto) throws RemoteException{

        Company company = CompanyConvertor.convert(companyDto);

       Set<Vehicle> vehicles =  companyDto.getVehicleDtos().stream()
                                        .map(VehicleConvetor::convert)
                                        .collect(Collectors.toSet());

        company.setVehicles(vehicles);
        vehicles.stream().forEach(s -> s.setClient(company));

        Optional<Company> optionalCompany = companyDao.findCompanyByName(company.getName());

        if(optionalCompany.isEmpty()){
           return companyDao.createCompany(company);
        }

        throw new IllegalArgumentException();
    }

    @Override
    public CompanyDto findById(int id) throws RemoteException{
       return CompanyConvertor.convert(companyDao.findCompanyById(id));
    }

    @Override
    public CompanyDto findCompanyByName(String name) throws RemoteException{

        return companyDao.findCompanyByName(name)
                .map(CompanyConvertor::convert)
                .orElseThrow(NoSuchElementException::new);
    }
}
