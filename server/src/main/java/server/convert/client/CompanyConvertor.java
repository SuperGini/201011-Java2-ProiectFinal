package server.convert.client;

import lib.dto.client.AdressDto;
import lib.dto.client.CompanyDto;
import server.model.client.Adress;
import server.model.client.Company;

import java.util.Optional;

public class CompanyConvertor {


    private CompanyConvertor() {
    }

    public static CompanyDto convert(Company company){

        CompanyDto companyDto = new CompanyDto.Builder()
                            .setIdDto(company.getId())
                            .setCuiDto(company.getCui())
                            .setNameDto(company.getName())
                            .build();


        Optional.ofNullable(company.getAdress())
                            .ifPresent(adress ->{
                                companyDto.setAdress( new AdressDto(
                                        adress.getStreet(),
                                        adress.getNumber()
                                ));
                            });

        return companyDto;
    }

    public static Company convert(CompanyDto companyDto){

         Company company = new Company.Builder()
                        .setCui(companyDto.getCui())
                        .setName(companyDto.getName())
                        .build();


         Optional.ofNullable(companyDto.getAdress())
                        .ifPresent(adress ->{
                            company.setAdress(new Adress(
                                    adress.getStreet(),
                                    adress.getNumber()
                            ));
                        });

         return company;

    }

}
