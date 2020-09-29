package server.convert.media;

import lib.dto.sound.SoundDto;
import server.model.sound.Sound;

public class SoundConvertor {

    private SoundConvertor() {
    }

    public static SoundDto convert(Sound sound){
        SoundDto soundDto = new SoundDto();
                    soundDto.setId(sound.getId());
                    soundDto.setNameSound(sound.getSoundName());
                    soundDto.setSoundWave(sound.getSoundWave());

        return soundDto;

    }
}
