package lib.service;

import lib.dto.BackgroundImageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BackgroundImageService extends Remote {

    List<BackgroundImageDto> getPicture() throws RemoteException;

}
