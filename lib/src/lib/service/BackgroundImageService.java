package lib.service;

import lib.dto.BackgroundImageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BackgroundImageService extends Remote {

    BackgroundImageDto getPicture() throws RemoteException;

}
