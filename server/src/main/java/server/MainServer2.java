package server;

import lib.dto.autovehicle.VehicleDto;
import lib.service.PersonService;
import lib.service.VehicleService;
import server.model.client.Person;
import server.service.autovehicle.VehicleServiceImpl;
import server.service.client.PersonServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.rmi.RemoteException;

public class MainServer2 {

    public static void main(String[] args) throws RemoteException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("serviceAuto");
        EntityManager em = emf.createEntityManager();
//
        VehicleService x = new VehicleServiceImpl();
        PersonService person = new PersonServiceImpl();

      //  Person p = em.find(Person.class, 502);

            Person p = new Person();
          //  p.setId(502);
            p.setName("gicafane");





//
//        Vehicle v2 = new Vehicle();
//        v2.setClient(p);
//        v2.setSerialNumber("incarcare3");
//
//        em.getTransaction().begin();
//        em.persist(v2);
//        em.getTransaction().commit();



        VehicleDto v = new VehicleDto();
        v.setSerialNumber("VVVVVVVVVVVVVVVVVVV");
    //    v.setClient();

        x.createVehicle(v);


    }







}
