# 201011-Java2-ProiectFinal



The app is created on 3 layers: 1 -> data base; 2 -> server; 3 -> client.         

To create the app I’m using JPA with Hibernate, ORM, RMI and Swing for the client side.

Waring! for the app to work it needs an external library that you can download from here:
https://spaces.hightail.com/receive/b2BMj

In the POM file you can find the GLG2D dependency that is used to throw the loading frame code on the video card. The GLG2D is not a stable code and can make your video card crash. The dependecy is not active.

The app is designed for the automobile industry and in special for the auto services.
The java code is divided in 3 parts.
1.	server:  contains the business logic, the entities that are mapped to the the tables on the databse.
2.	lib: that containes the dto’s and the interfaces that connect the server and the client throuh RMI.
3.	client: contains all the view pages and components made with swing and alsow the controllers.

Client side views:
1.	RegisterPage: you can create new users here
2.	LoginPage: you enter your credentials here (username and password) to login into the app.
3.	CreateClientAndVehicle: here you can add new clients and vehicle to the database so you can use them in the process of creating a service order.
4.	PartPage: here you can add parts to the service order. Before adding parts to an order you have to select firts and order that is is OPEN or READY from the order table in the CreateOrderPage.
5.	CreateOrderPage: here you can create service orders, by adding clients and vehicle. You can add vehicle to the order by searching the vehicles by the VIN number.

The program is used to create service order to estimate and bill the car repaire costs. For the app to work it needs 2 types of users. WAREHOUSE: can only add parts to the service orders, and BODY or MECHANICAL users that can create the service orders.
To create this app i used a MySql database but it can work with any relationsip database, do to the usage of abstraction offerd by JPA and JPQL. The database and tables are created automatically. The app has 2 main methods: one for the server and one for the client. IDE used: IntelliJ IDEA.

Warning!! the sounds and pictures from this program don't belog to me. The pictures you can find them here https://unsplash.com/ All pictures and sounds are used only for documentation or learnig purpose they are not for comercial use. If you dont like to use your picture or sounds write me here: mihai.iordache82@gmail.com and i will take them down.

p.s: DOWNLOAD THE CODE RUN THE CODE AND HAVE FUN -> the client side is awesome:DD




































