package client.util.sound;

import client.controller.media.SoundController;
import lib.dto.sound.SoundDto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class SoundConvertor {

    private Set<SoundDto> soundDtos = SoundController.getInstance().findAllSounds();


    public SoundConvertor() {
        convertAllSounds();
    }


    private void convertAllSounds(){

        soundDtos.forEach(this::convertSoundDataToFile);
    }

    //transform data de tip byte[] -> wav file
    private void convertSoundDataToFile(SoundDto soundDto){

        String soundPath = "./client/src/main/resources/sounds/" + soundDto.getNameSound();

        Path verifyPath = Paths.get(soundPath);
        System.out.println(verifyPath);

        if(!Files.exists(verifyPath)){

            try( FileOutputStream fos = new FileOutputStream(soundPath)){

                fos.write(soundDto.getSoundWave());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
