package server.dao;

import server.model.picture.BackgroundImage;

import java.nio.file.Path;
import java.util.List;

public interface BackGroundImageDao {
    void sendPicturesToDatabase(BackgroundImage backgroundImage, Path path);

    List<BackgroundImage> findAllBackgroundPictures();
}
