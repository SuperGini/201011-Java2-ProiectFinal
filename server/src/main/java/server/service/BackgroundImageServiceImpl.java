package server.service;

import lib.dto.BackgroundImageDto;
import lib.service.BackgroundImageService;
import server.convert.BackgroundImageConvertor;
import server.dao.BackGroundImageDao;
import server.dao.impl.BackGroundImageDaoImpl;
import server.model.picture.BackgroundImage;

import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BackgroundImageServiceImpl extends UnicastRemoteObject implements BackgroundImageService {

    private final BackGroundImageDao backGroundImageDao;
    private Path path = Paths.get("./server/src/main/resources/images");


    public BackgroundImageServiceImpl() throws RemoteException {

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


    private List<BackgroundImage> findAllBackgroundPictures(){
      return  backGroundImageDao.findAllBackgroundPictures();
    }


    @Override
    public List<BackgroundImageDto> getPicture(){
        Random random = new Random();

      return    findAllBackgroundPictures().stream()
                .map( picture-> findAllBackgroundPictures().get(random.nextInt(findAllBackgroundPictures().size())))
                .map(BackgroundImageConvertor::convert)
                .collect(Collectors.toList());

      //todo: de transformat listra dintr-o imagine intr-o lista completa pe care sa fac random cu un thread
        //atentie ca selecteaza toate pozele din baza de date -> era bine sa faca random doar pe o poza din baza de date

    }




}
