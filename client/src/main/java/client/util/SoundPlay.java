package client.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SoundPlay {

   private List<Clip> clips = new ArrayList<>();
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

    public List<Clip> getClips() {
        return clips;
    }
}
