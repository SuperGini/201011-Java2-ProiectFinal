package server.dao.impl;

import server.dao.BackGroundImageDao;
import server.model.picture.BackgroundImage;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BackGroundImageDaoImpl implements BackGroundImageDao {

    private EntityManager entityManager;

    public BackGroundImageDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void sendPicturesToDatabase(BackgroundImage backgroundImage, Path path){

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
    public List<BackgroundImage> findAllBackgroundPictures(){
        TypedQuery<BackgroundImage> query = entityManager.createNamedQuery("BackgroundImage.findAll", BackgroundImage.class);

        return query.getResultList();

    }

}
