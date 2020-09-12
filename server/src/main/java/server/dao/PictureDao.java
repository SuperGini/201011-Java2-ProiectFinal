package server.dao;

import server.model.picture.Picture;

import java.nio.file.Path;
import java.util.List;

public interface PictureDao {
    void sendPicturesToDatabase(Picture backgroundImage, Path path);

    List<Picture> findAllBackgroundPictures();
}
