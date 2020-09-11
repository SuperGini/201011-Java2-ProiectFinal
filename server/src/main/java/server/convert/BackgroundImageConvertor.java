package server.convert;
import lib.dto.BackgroundImageDto;
import server.model.picture.BackgroundImage;

public class BackgroundImageConvertor {

    private BackgroundImageConvertor() {
    }


    public static BackgroundImageDto convert(BackgroundImage backgroundImage){

        BackgroundImageDto backgroundImageDto = new BackgroundImageDto(
                                                backgroundImage.getId(),
                                                backgroundImage.getPicture());

        return backgroundImageDto;
    }





}
