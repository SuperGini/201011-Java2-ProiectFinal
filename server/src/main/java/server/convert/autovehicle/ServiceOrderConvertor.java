package server.convert.autovehicle;

import lib.dto.autovehicle.ServiceOrderDto;
import server.model.autovehicle.ServiceOrder;

public class ServiceOrderConvertor {

    private ServiceOrderConvertor() {
    }

    public static ServiceOrderDto convert(ServiceOrder serviceOrder){
        ServiceOrderDto serviceOrderDto = new ServiceOrderDto(
                                              serviceOrder.getId(),
                                              serviceOrder.getTotal()

        );

        serviceOrderDto.setCarProblems(serviceOrder.getCarProblems());

        return serviceOrderDto;

    }

    public static ServiceOrder convert(ServiceOrderDto serviceOrderDto){
        ServiceOrder serviceOrder =  new ServiceOrder(
                serviceOrderDto.getId(),
                serviceOrderDto.getTotal()

        );

        serviceOrder.setCarProblems(serviceOrderDto.getCarProblems());

        return serviceOrder;
    }
}
