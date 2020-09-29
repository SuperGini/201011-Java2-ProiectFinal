package client.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class SoundPlay {

   private Set<Clip> clips = new HashSet<>();
   private final Path path = Paths.get("./client/src/main/resources/sounds/");

    public SoundPlay() {
        setClips();
    }

    //transform fisierul wav in clip ca sa pot lucra cu sunetul
    public Clip getSound(String soundPath){
        Clip clip =null;

            try(
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile())
            ){

                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                return clip;
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException x) {
                x.printStackTrace();
            }

        return clip;
    }

    private void setClips(){

        try {
            Files.list(path)
                    .map(Path::toString)
                    .map(this::getSound)
                    .forEach(clips::add);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<Clip> getClips() {
        return clips;
    }
}
