package server.convert;
import lib.dto.picture.PictureDto;
import server.model.picture.Picture;

public class PictureConvertor {

    private PictureConvertor() {
    }


    public static PictureDto convert(Picture backgroundImage){

        PictureDto backgroundImageDto = new PictureDto(
                                                backgroundImage.getId(),
                                                backgroundImage.getPicture());

        return backgroundImageDto;
    }





}
