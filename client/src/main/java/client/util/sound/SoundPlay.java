package client.util.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class SoundPlay {

   private Map<String, Clip> clips = new HashMap<>();
   private final Path path = Paths.get("./client/src/main/resources/sounds/");


    public SoundPlay() {
        loadClips();
    }

    //transform fisierul wav in clip ca sa pot lucra cu sunetul
    public Clip getSound(Path path){
        String soundPath = path.toString();

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

    private void loadClips(){

        try {

            Files.list(path)
                    .forEach( sound-> clips.put(sound.getFileName().toString(),getSound(sound)));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Clip> getClips() {
        return clips;
    }

    public void setClips(Map<String, Clip> clips) {
        this.clips = clips;
    }
}
