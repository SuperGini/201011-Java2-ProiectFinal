package server;

import server.model.autovehicle.Part;
import server.model.autovehicle.ServiceOrder;
import server.model.autovehicle.Vehicle;
import server.model.client.Adress;
import server.model.client.Client;
import server.model.client.Company;
import server.model.client.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainServer2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("serviceAuto");
        EntityManager em = emf.createEntityManager();
//
//
        Client x = new Client();



        Adress s = new Adress();
        s.setNumber("5");
        s.setStreet("xxx");


        Person p = new Person();

        p.setName("hjkj");
        p.setAdress(s);
        p.setCnp("182564785");


        Company c = new Company();

        c.setName("intel");
        c.setCui("6589");
        c.setAdress(s);





        //*******************
//
        Part part = new Part();

        part.setCount(5);
        part.setPartName("bujie");

        //**********


//        ServiceOrder order = new ServiceOrder();
//        order.setName("vvvvvv");
//
//        order.setClient(p);
//        order.setCarProblems(List.of("cacat"));
//        order.setParts(Set.of(parts));

        //************************************


        Vehicle vehicle = new Vehicle();

     //   vehicle.setClient(em.find(Client.class, 10));
        vehicle.setSerialNumber("lopolol");
       // vehicle.setClient(p);


        //=========

        ServiceOrder m = new ServiceOrder();

//        m.setClient(em.find(Client.class, 3));
//        m.setParts(Set.of(em.find(Parts.class, 1)));
//        m.setCarProblems(List.of("zzzzzzzzzzx"));
//        m.setVehicle(em.find(Vehicle.class, 11));
//        p.setServiceOrders(Set.of(em.find(ServiceOrder.class, 19)));

    //    p.setVehicles(Set.of(vehicle));



        em.getTransaction().begin();
    //   em.persist(parts);
       em.persist(p);
      //  em.persist(c);
      //  em.persist(m);
      //  em.persist(parts);
          em.persist(vehicle);


        em.getTransaction().commit();





    }







}
