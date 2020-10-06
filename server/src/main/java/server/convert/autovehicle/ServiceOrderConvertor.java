package server.convert.autovehicle;

import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.VehicleDto;
import lib.dto.client.ClientDto;
import lib.dto.user.UserDto;
import server.convert.client.ClientConvertor;
import server.convert.user.UserConvertor;
import server.model.autovehicle.ServiceOrder;
import server.model.autovehicle.Vehicle;
import server.model.client.Client;
import server.model.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ServiceOrderConvertor {

    private ServiceOrderConvertor() {
    }

    public static ServiceOrderDto convert(ServiceOrder serviceOrder){

        VehicleDto vehicleDto = VehicleConvetor.convert(serviceOrder.getVehicle());
        ClientDto clientDto = ClientConvertor.convert(serviceOrder.getClient());
        UserDto userDto = UserConvertor.convert(serviceOrder.getUser());
        Collection<PartDto> partsDto = serviceOrder.getParts()
                                    .stream()
                                    .map(PartConvertor::convert)
                                    .collect(Collectors.toList());

//        ServiceOrderDto serviceOrderDto = new ServiceOrderDto(
//                                              serviceOrder.getId(),
//                                              serviceOrder.getTotal()
//
//        );
//
//
//        serviceOrderDto.setParts(new ArrayList<>(partsDto));
//        serviceOrderDto.setUserDto(userDto);
//        serviceOrderDto.setStatus(serviceOrder.getStatus());
//        serviceOrderDto.setVehicleDtos(vehicleDto);
//        serviceOrderDto.setClientDto(clientDto);
//        serviceOrderDto.setCarProblems(new ArrayList<>(serviceOrder.getCarProblems()));
//        serviceOrderDto.setPartsIds(null);

        ServiceOrderDto serviceOrderDto1 = new ServiceOrderDto.Builder()
                                          .setCarProblems(new ArrayList<>(serviceOrder.getCarProblems()))
                                          .setParts(new ArrayList<>(partsDto))
                                          .setStatus(serviceOrder.getStatus())
                                          .setTotal(serviceOrder.getTotal())
                                          .setId(serviceOrder.getId())
                                          .setVehicle(vehicleDto)
                                          .setClient(clientDto)
                                          .setUser(userDto)
                                          .build();

        return serviceOrderDto1;

    }

    public static ServiceOrder convert(ServiceOrderDto serviceOrderDto){
        Client client = ClientConvertor.convert(serviceOrderDto.getClientDto());
        Vehicle vehicle = VehicleConvetor.convert(serviceOrderDto.getVehicleDtos());
        User user = UserConvertor.convert(serviceOrderDto.getUserDto());

//        ServiceOrder serviceOrder =  new ServiceOrder(
//                serviceOrderDto.getId(),
//                serviceOrderDto.getTotal()
//
//        );
//
//        serviceOrder.setCarProblems(serviceOrderDto.getCarProblems());
//
//        serviceOrder.setClient(client);
//        serviceOrder.setVehicle(vehicle);
//        serviceOrder.setUser(user);
//        serviceOrder.setStatus(serviceOrderDto.getStatus());



        ServiceOrder serviceOrder1 = new ServiceOrder.Builder()
                                    .setCarProblems(serviceOrderDto.getCarProblems())
                                    .setStatus(serviceOrderDto.getStatus())
                                    .setTotal(serviceOrderDto.getTotal())
                                    .setId(serviceOrderDto.getId())
                                    .setVehicle(vehicle)
                                    .setClient(client)
                                    .setUser(user)
                                    .build();

        return serviceOrder1;
    }
}
