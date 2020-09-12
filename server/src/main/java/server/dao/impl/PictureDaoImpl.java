package server.dao.impl;

import server.dao.PictureDao;
import server.model.picture.Picture;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PictureDaoImpl implements PictureDao {

    private EntityManager entityManager;

    public PictureDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void sendPicturesToDatabase(Picture backgroundImage, Path path){

        try {

            backgroundImage.setPicture(Files.readAllBytes(path)); //might cause OutOfMemoryError -> for big pictures

            entityManager.getTransaction().begin();
            entityManager.persist(backgroundImage);
            entityManager.getTransaction().commit();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Picture> findAllBackgroundPictures(){
        TypedQuery<Picture> query = entityManager.createNamedQuery("Picture.findAll", Picture.class);

        return query.getResultList();

    }

}
