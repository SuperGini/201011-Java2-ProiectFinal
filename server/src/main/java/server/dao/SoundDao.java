package server.dao;

import server.model.sound.Sound;

import java.nio.file.Path;
import java.util.List;

public interface SoundDao {
    boolean sendSoundToDatabase(Sound sound, Path path);

    List<Sound> findAllSounds();
}
