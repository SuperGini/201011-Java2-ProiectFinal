package server.convert.autovehicle;

import lib.dto.autovehicle.PartDto;
import server.model.autovehicle.Part;
import server.model.autovehicle.ServiceOrder;

import java.util.ArrayList;
import java.util.List;

public class PartConvertor {


    private PartConvertor() {
    }

    public static PartDto convert(Part part){
        PartDto partdto = new PartDto(
                          part.getId(),
                          part.getPartName(),
                          part.getPrice(),
                          part.getCount()

        );

    //    partdto.setTotalNumberOfParts(new ArrayList<>(part.getTotalNumberOfParts()));


        return partdto;

    }

    public static Part convert(PartDto partDto){
        Part part = new  Part(
                    partDto.getId(),
                    partDto.getPartName(),
                    partDto.getPrice(),
                    partDto.getCount()
        );

        List<ServiceOrder> serviceOrders = new ArrayList<>();
        serviceOrders.add(ServiceOrderConvertor.convert(partDto.getServiceOrderDto()));

        part.setOrders(serviceOrders);
     //   part.setTotalNumberOfParts(partDto.getTotalNumberOfParts());

        return part;
    }
}
