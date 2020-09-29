package client.controller.media;

import lib.dto.sound.SoundDto;
import lib.service.SoundService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

public class SoundController implements SoundService {

private final SoundService soundService;



    public SoundController() {
        try {
            Registry registry = LocateRegistry.getRegistry(4545);
            soundService = (SoundService) registry.lookup("soundService");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Set<SoundDto> findAllSounds(){
        try {
           return soundService.findAllSounds();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder{
        public static final SoundController INSTANCE = new SoundController();
    }

    public static SoundController getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public SoundService getSoundService() {
        return soundService;
    }
}
