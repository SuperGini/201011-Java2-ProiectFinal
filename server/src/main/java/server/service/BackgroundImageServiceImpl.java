package server.service;

import server.dao.BackGroundImageDao;
import server.dao.impl.BackGroundImageDaoImpl;
import server.model.picture.BackgroundImage;

import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BackgroundImageServiceImpl {

    private final BackGroundImageDao backGroundImageDao;
    private Path path = Paths.get("./server/src/main/resources/images");


    public BackgroundImageServiceImpl() {

        var entityManageFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManageFactory.createEntityManager();

        this.backGroundImageDao = new BackGroundImageDaoImpl(entityManager);
    }

    //method 2
    public void sendPicturesToDatabase(){

        try {

            if(findAllBackgroundPictures().isEmpty()){
                Files.list(path)
                        .forEach(this::sendPicture);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //method 1
    private void sendPicture(Path path){
        BackgroundImage image = new BackgroundImage();
        backGroundImageDao.sendPicturesToDatabase(image, path);
    }


    public List<BackgroundImage> findAllBackgroundPictures(){
      return  backGroundImageDao.findAllBackgroundPictures();

    }




}
