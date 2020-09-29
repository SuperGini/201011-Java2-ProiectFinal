package server.service.media;

import lib.dto.sound.SoundDto;
import server.convert.media.SoundConvertor;
import server.dao.impl.media.SoundDaoImpl;
import server.dao.interfaces.SoundDao;
import server.model.sound.Sound;

import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;
import java.util.stream.Collectors;

public class SoundServiceImpl extends UnicastRemoteObject implements lib.service.SoundService {

    private final SoundDao soundDao;
    private Path soundPath = Paths.get("./server/src/main/resources/sounds");
    private Path y = Paths.get("./server/src/main/resources/sounds/sound4.wav");



    public SoundServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManage = entityManagerFactory.createEntityManager();
        this.soundDao = new SoundDaoImpl(entityManage);
        sendSoundToDatabase();

    }


    public void sendSoundToDatabase(){

        try {
            if(findAllSounds().isEmpty()){
                try {
                    Files.list(soundPath)
                            .forEach(this::sendSound);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    private void sendSound(Path path){
        Sound sound = new Sound();
        soundDao.sendSoundToDatabase(sound, path);

    }

    @Override
    public Set<SoundDto> findAllSounds() throws RemoteException{

       return soundDao.findAllSounds().stream()
                  .map(SoundConvertor::convert)
                  .collect(Collectors.toSet());
    }
}
