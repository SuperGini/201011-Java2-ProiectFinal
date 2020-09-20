package server.convert.autovehicle;

import lib.dto.autovehicle.ServiceOrderDto;
import server.model.autovehicle.ServiceOrder;

public class ServiceOrderConvertor {

    private ServiceOrderConvertor() {
    }

    public static ServiceOrderDto convert(ServiceOrder serviceOrder){
        return new ServiceOrderDto(
                serviceOrder.getId(),
                serviceOrder.getTotal(),
                serviceOrder.getTimeStamp()
        );
    }

    public static ServiceOrder convert(ServiceOrderDto serviceOrderDto){
        return new ServiceOrder(
                serviceOrderDto.getId(),
                serviceOrderDto.getTotal(),
                serviceOrderDto.getTimeStamp()
        );
    }
}
