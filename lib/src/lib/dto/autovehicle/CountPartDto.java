package lib.dto.autovehicle;


import java.io.Serializable;

public class CountPartDto implements Serializable {

    private int id;

    private int countPartDto;

    private ServiceOrderDto serviceOrderDto;

    public int getCountPartDto() {
        return countPartDto;
    }

    public void setCountPartDto(int countPartDto) {
        this.countPartDto = countPartDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServiceOrderDto getServiceOrderDto() {
        return serviceOrderDto;
    }

    public void setServiceOrderDto(ServiceOrderDto serviceOrderDto) {
        this.serviceOrderDto = serviceOrderDto;
    }
}
