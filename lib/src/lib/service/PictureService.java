package lib.service;

import lib.dto.PictureDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PictureService extends Remote {


 //   void sendPicturesToDatabase();

    PictureDto getPicture() throws RemoteException;

}
