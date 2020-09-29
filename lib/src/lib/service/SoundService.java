package lib.service;

import lib.dto.sound.SoundDto;

import java.rmi.RemoteException;
import java.util.Set;

public interface SoundService {

    Set<SoundDto> findAllSounds() throws RemoteException;
}
