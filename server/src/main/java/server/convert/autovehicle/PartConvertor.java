package server.convert.autovehicle;

import lib.dto.autovehicle.PartDto;
import server.model.autovehicle.Part;
import server.model.autovehicle.ServiceOrder;

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

       ServiceOrder serviceOrder = ServiceOrderConvertor.convert(partDto.getServiceOrderDto());

        part.setOrders(serviceOrder);
     //   part.setTotalNumberOfParts(partDto.getTotalNumberOfParts());

        return part;
    }
}
