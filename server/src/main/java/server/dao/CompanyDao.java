package server.dao;

import server.model.client.Company;

import java.util.Optional;

public interface CompanyDao {
    boolean createCompany(Company company);

    Company findCompanyById(int id);

    Optional<Company> findCompanyByName(String name);
}
