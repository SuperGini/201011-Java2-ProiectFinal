package lib.service;

import lib.dto.client.CompanyDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CompanyService extends Remote {

    boolean ceateCompany(CompanyDto companyDto) throws RemoteException;

    CompanyDto findById(int id) throws RemoteException;

    CompanyDto findCompanyByName(String name) throws RemoteException;
}
