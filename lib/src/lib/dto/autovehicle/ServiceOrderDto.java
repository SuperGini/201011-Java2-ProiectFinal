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


    public ServiceOrderDto(int id, double total) {
        this.id = id;
        this.total = total;

    }
    public ServiceOrderDto() {
    }



    public static class Builder{
        private ServiceOrderDto serviceOrderDto = new ServiceOrderDto();

        public Builder setId(int id){
            serviceOrderDto.id = id;
            return this;
        }

        public Builder setTotal(double total){
            serviceOrderDto.total = total;
            return this;
        }

        public Builder setClient(ClientDto clientDto){
            serviceOrderDto.clientDto = clientDto;
            return this;
        }

        public Builder setUser(UserDto userDto){
            serviceOrderDto.userDto = userDto;
            return this;
        }

        public Builder setVehicle(VehicleDto vehicleDto){
            serviceOrderDto.vehicleDtos = vehicleDto;
            return this;
        }

        public Builder setStatus(Status status){
            serviceOrderDto.status = status;
            return this;
        }

        public Builder setCarProblems(List<String> carProblems){
            serviceOrderDto.carProblems = carProblems;
            return this;
        }

        public Builder setParts(List<PartDto> parts){
            serviceOrderDto.parts = parts;
            return this;
        }

        public ServiceOrderDto build(){
            return serviceOrderDto;
        }


    }


    public List<PartDto> getParts() {
        return parts;
    }

    public List<Integer> getPartsIds() {
        return partsIds;
    }

    public void setPartsIds(List<Integer> partsIds) {
        this.partsIds = partsIds;
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
