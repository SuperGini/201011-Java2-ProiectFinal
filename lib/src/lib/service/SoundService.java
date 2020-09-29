package lib.service;

import lib.dto.sound.SoundDto;

import java.util.Set;

public interface SoundService {
    
    Set<SoundDto> findAllSounds();
}
