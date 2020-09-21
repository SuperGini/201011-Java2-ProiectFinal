package lib.dto.client;

import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.VehicleDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class ClientDto implements Serializable {

    private int id;

    private String name;

    private AdressDto adress;

    private Collection<VehicleDto> vehicleDtos = new HashSet<>();

    private Collection<ServiceOrderDto> serviceOrderDtos = new ArrayList<>();



    public ClientDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdressDto getAdress() {
        return adress;
    }

    public void setAdress(AdressDto adress) {
        this.adress = adress;
    }

    public Collection<VehicleDto> getVehicleDtos() {
        return vehicleDtos;
    }

    public void setVehicleDtos(Collection<VehicleDto> vehicleDtos) {
        this.vehicleDtos = vehicleDtos;
    }

    public Collection<ServiceOrderDto> getServiceOrderDtos() {
        return serviceOrderDtos;
    }

    public void setServiceOrderDtos(Collection<ServiceOrderDto> serviceOrderDtos) {
        this.serviceOrderDtos = serviceOrderDtos;
    }
}
