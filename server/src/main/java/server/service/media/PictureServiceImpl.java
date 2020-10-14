package server.service.media;

import lib.dto.picture.PictureDto;
import lib.service.PictureService;
import server.convert.media.PictureConvertor;
import server.dao.interfaces.PictureDao;
import server.dao.impl.media.PictureDaoImpl;
import server.model.picture.Picture;
import server.util.BlurrImage;

import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class PictureServiceImpl extends UnicastRemoteObject implements PictureService {

    private final PictureDao pictureDao;
    private Path pathBlurr = Paths.get("./server/src/main/resources/blurredImages");
    private Path pathOriginal = Paths.get("./server/src/main/resources/images");
    private  List<PictureDto> pictures = new CopyOnWriteArrayList<>();


    public PictureServiceImpl() throws RemoteException {

        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        this.pictureDao = new PictureDaoImpl(entityManager);
        addPictureToList();
        sendPicturesToDatabase();

    }

    //method 1
    public void sendPicturesToDatabase(){

        try {
            if(pictures.isEmpty()){

                Files.list(pathOriginal)
                        .map(Path::toString)
                        .forEach(BlurrImage::getBufferdImage);


                Files.list(pathBlurr)
                        .forEach(this::sendPicture);


                Files.list(pathBlurr)
                        .forEach( blurredPicture-> {
                            try {

                               Files.deleteIfExists(blurredPicture);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //method 2
    private void sendPicture(Path path){
        Picture image = new Picture();
        pictureDao.sendPicturesToDatabase(image, path);
    }

    
    private void addPictureToList(){
       pictureDao.findAllBackgroundPictures().stream()

                .map(PictureConvertor::convert)
                .forEach(pictures::add);
    }

    @Override
    public PictureDto getPicture() throws RemoteException {
        Random random = new Random();

        return pictures.get(random.nextInt(pictures.size()));
    }
}
