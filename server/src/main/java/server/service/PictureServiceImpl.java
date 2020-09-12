package server.service;

import lib.dto.picture.PictureDto;
import lib.service.PictureService;
import server.convert.PictureConvertor;
import server.dao.PictureDao;
import server.dao.impl.PictureDaoImpl;
import server.model.picture.Picture;

import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Random;

public class PictureServiceImpl extends UnicastRemoteObject implements PictureService {

    private final PictureDao pictureDao;
    private Path path = Paths.get("./server/src/main/resources/images");


    public PictureServiceImpl() throws RemoteException {

        var entityManageFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManageFactory.createEntityManager();

        this.pictureDao = new PictureDaoImpl(entityManager);
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
        Picture image = new Picture();
        pictureDao.sendPicturesToDatabase(image, path);
    }


    private List<Picture> findAllBackgroundPictures(){
      return  pictureDao.findAllBackgroundPictures();
    }


    @Override
    public PictureDto getPicture() throws RemoteException{
        Random random = new Random();

      return    findAllBackgroundPictures().stream()
                .map( picture-> findAllBackgroundPictures().get(random.nextInt(findAllBackgroundPictures().size())))
                .map(PictureConvertor::convert)
                .findFirst()
              .get();

      //todo: de transformat dintr-o imagine intr-o lista completa pe care sa fac random cu un thread
        //todo: atentie de bagat pozele intr-o colectie ce este pastrata pe server doarece de fiecare cand se conecteaza
        //todo: un client metoda face un select de poze din baza de date.


    }




}
