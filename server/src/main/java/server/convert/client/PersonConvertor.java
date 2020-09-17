package server.convert.client;

import lib.dto.client.AdressDto;
import lib.dto.client.PersonDto;
import server.model.client.Adress;
import server.model.client.Person;

import java.util.Optional;

public class PersonConvertor {

    private PersonConvertor() {
    }

    public static PersonDto convert(Person person){

        PersonDto personDto = new PersonDto.Builder()
                            .setCnpDto(person.getCnp())
                            .setNameDto(person.getName())
                            .setIdDto(person.getId())
                            .build();


        Optional.ofNullable(person.getAdress())
                            .ifPresent(adressDto ->{
                               personDto.setAdress( new AdressDto(
                                       person.getAdress().getStreet(),
                                       person.getAdress().getStreet()
                               ));

                            });

        return personDto;
    }


    public static Person convert(PersonDto personDto){

        Person person = new Person.Builder()
                        .setCnp(personDto.getCnp())
                        .setName(personDto.getName())
                        .build();

        Optional.ofNullable(personDto.getAdress())
                .ifPresent(adress ->{
                    person.setAdress( new Adress(
                            personDto.getAdress().getStreet(),
                            personDto.getAdress().getNumber()
                    ));
                });


        return person;

    }
}
