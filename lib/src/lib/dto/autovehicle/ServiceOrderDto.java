package lib.dto.autovehicle;

import lib.dto.client.ClientDto;
import lib.dto.user.UserDto;

import java.io.Serializable;
import java.util.*;

public class ServiceOrderDto implements Serializable {

    private int id;

    private int partCount;

    private double total;



    private List<String> carProblems = new ArrayList<>();

    private Collection<PartDto> parts = new HashSet<>();

    private Set<Integer> partsIds = new HashSet<>();

    private VehicleDto vehicleDtos;

    private UserDto userDto;

    private ClientDto clientDto;



    public ServiceOrderDto(int id, double total) {
        this.id = id;
        this.total = total;

    }

    public Set<Integer> getPartsIds() {
        return partsIds;
    }

    public void setPartsIds(Set<Integer> partsIds) {
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

    public Collection<PartDto> getParts() {
        return parts;
    }

    public void setParts(Collection<PartDto> parts) {
        this.parts = parts;
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

    public int getPartCount() {
        return partCount;
    }

    public void setPartCount(int partCount) {
        this.partCount = partCount;
    }
}
