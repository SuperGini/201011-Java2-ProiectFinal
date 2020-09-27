package server.convert.autovehicle;

import lib.dto.autovehicle.CountPartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import server.model.autovehicle.CountPart;
import server.model.autovehicle.ServiceOrder;

public class CountParConvertor {


    private CountParConvertor() {
    }


    public static CountPart convet(CountPartDto countPartDto){

        ServiceOrder serviceOrder = ServiceOrderConvertor.convert(countPartDto.getServiceOrderDto());

        CountPart countPart = new CountPart();
        countPart.setCountPart(countPartDto.getCountPartDto());
        countPart.setId(countPartDto.getId());
        countPart.setServiceOrder(serviceOrder);

        return countPart;

    }


    public static CountPartDto convert(CountPart countPart){
        CountPartDto countPartDto = new CountPartDto();
        ServiceOrderDto serviceOrderDto = ServiceOrderConvertor.convert(countPart.getServiceOrder());

        countPartDto.setCountPartDto(countPart.getCountPart());
        countPartDto.setId(countPart.getId());
        countPartDto.setServiceOrderDto(serviceOrderDto);

        return countPartDto;
    }
}
