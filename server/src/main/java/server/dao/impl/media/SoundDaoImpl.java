package server.dao.impl.media;

import server.dao.interfaces.SoundDao;
import server.model.sound.Sound;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SoundDaoImpl implements SoundDao {

    private EntityManager entityManager;

    public SoundDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean sendSoundToDatabase(Sound sound, Path path){

        try {

            sound.setSoundWave(Files.readAllBytes(path));
            sound.setSoundName(path.getFileName().toString());

            entityManager.getTransaction().begin();
            entityManager.persist(sound);
            entityManager.getTransaction().commit();
            entityManager.getTransaction().getRollbackOnly();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Sound> findAllSounds(){
        var query = entityManager.createNamedQuery("findAllSounds", Sound.class);

        return query.getResultList();
    }


}
