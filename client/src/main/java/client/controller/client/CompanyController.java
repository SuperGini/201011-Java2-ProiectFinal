package client.controller.client;

import lib.dto.client.CompanyDto;
import lib.service.CompanyService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CompanyController implements CompanyService {

    private CompanyService companyService;

    private CompanyController() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            companyService = (CompanyService) registry.lookup("companyService");

        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean ceateCompany(CompanyDto companyDto){
        try {
            return companyService.ceateCompany(companyDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompanyDto findById(int id){
        try {
            return companyService.findById(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompanyDto findCompanyByName(String name){
        try {
            return companyService.findCompanyByName(name);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private static final class SingletonHolder{
        public static final CompanyController INSTANCE = new CompanyController();
    }

    public static CompanyController getInstance(){
        return SingletonHolder.INSTANCE;

    }
}
