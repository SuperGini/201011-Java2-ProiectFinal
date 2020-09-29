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
import java.util.Set;
import java.util.stream.Collectors;

public class SoundServiceImpl implements lib.service.SoundService {

    private final SoundDao soundDao;
    private final Path soundPath = Paths.get("./server/src/main/resources/sounds");



    public SoundServiceImpl(SoundDao soundDao) {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManage = entityManagerFactory.createEntityManager();
        this.soundDao = new SoundDaoImpl(entityManage);
    }


    public void sendSoundToDatabase(){
            Sound sound = new Sound();

        try {
            Files.list(soundPath)
                    .forEach(s ->soundDao.sendSoundToDatabase(sound,soundPath ));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Set<SoundDto> findAllSounds(){

       return soundDao.findAllSounds().stream()
                  .map(SoundConvertor::convert)
                  .collect(Collectors.toSet());
    }
}
