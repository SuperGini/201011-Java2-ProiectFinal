package server.convert.autovehicle;

import lib.dto.autovehicle.PartDto;
import server.model.autovehicle.Part;

public class PartConvertor {


    private PartConvertor() {
    }

    public static PartDto convert(Part part){
        return new PartDto(
                        part.getId(),
                        part.getPartName(),
                        part.getPrice(),
                        part.getCount()

        );
    }

    public static Part convert(PartDto partDto){
        return  new Part(
                    partDto.getPartName(),
                    partDto.getPrice(),
                    partDto.getCount()
        );
    }
}
