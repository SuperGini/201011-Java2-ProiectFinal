package lib.service;

import lib.dto.sound.SoundDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface SoundService extends Remote {

    Set<SoundDto> findAllSounds() throws RemoteException;
}
