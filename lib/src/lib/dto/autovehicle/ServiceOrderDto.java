package lib.dto.autovehicle;

import lib.dto.client.ClientDto;
import lib.dto.user.UserDto;

import java.io.Serializable;
import java.util.*;

public class ServiceOrderDto implements Serializable {

    private int id;

    private double total;

    private VehicleDto vehicleDtos;

    private UserDto userDto;

    private ClientDto clientDto;

    private Status status;


    private List<String> carProblems = new ArrayList<>();

    private List<PartDto> parts = new ArrayList<>();


    private List<Integer> partsIds = new ArrayList();

    private List<CountPartDto> countPartDtos = new ArrayList<>();


    public ServiceOrderDto(int id, double total) {
        this.id = id;
        this.total = total;

    }

    public List<PartDto> getParts() {
        return parts;
    }

    public List<Integer> getPartsIds() {
        return partsIds;
    }

    public List<CountPartDto> getCountPartDtos() {
        return countPartDtos;
    }

    public void setCountPartDtos(List<CountPartDto> countPartDtos) {
        this.countPartDtos = countPartDtos;
    }

    public void setPartsIds(List<Integer> partsIds) {
        this.partsIds = partsIds;
    }

    public ServiceOrderDto() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public List<String> getCarProblems() {
        return carProblems;
    }

    public void setCarProblems(List<String> carProblems) {
        this.carProblems = carProblems;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ClientDto getClientDto() {
        return clientDto;
    }

    public void setClientDto(ClientDto clientDto) {
        this.clientDto = clientDto;
    }

    public VehicleDto getVehicleDtos() {
        return vehicleDtos;
    }

    public void setVehicleDtos(VehicleDto vehicleDtos) {
        this.vehicleDtos = vehicleDtos;
    }

    public void setParts(List<PartDto> parts) {
        this.parts = parts;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
