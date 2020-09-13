package lib.service;

import lib.dto.picture.PictureDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PictureService extends Remote {

    PictureDto getPicture() throws RemoteException;
}
