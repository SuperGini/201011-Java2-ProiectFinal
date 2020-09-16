package server.dao;

import server.model.client.Company;

public interface CompanyDao {
    boolean createCompany(Company company);

    Company findCompanyById(int id);
}
