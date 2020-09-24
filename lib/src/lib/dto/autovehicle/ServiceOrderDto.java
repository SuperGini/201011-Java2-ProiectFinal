package lib.dto.autovehicle;

import lib.dto.client.ClientDto;
import lib.dto.user.UserDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class ServiceOrderDto implements Serializable {

    private int id;

    private double total;

   // private Instant timeStamp;

    private List<String> carProblems = new ArrayList<>();

    private Collection<PartDto> parts = new HashSet<>();

    private VehicleDto vehicleDtos;

    private UserDto userDto;

    private ClientDto clientDto;

    private String idUsername;

    private int idClient;

    private int idVehicul;

    public ServiceOrderDto(int id, double total) {
        this.id = id;
        this.total = total;

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


    public String getIdUsername() {
        return idUsername;
    }

    public void setIdUsername(String idUsername) {
        this.idUsername = idUsername;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdVehicul() {
        return idVehicul;
    }

    public void setIdVehicul(int idVehicul) {
        this.idVehicul = idVehicul;
    }
}
