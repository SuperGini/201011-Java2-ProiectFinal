package server.service.autovehicle;

import lib.dto.autovehicle.PartDto;
import lib.dto.autovehicle.ServiceOrderDto;
import lib.dto.autovehicle.Status;
import lib.dto.bill.BillDto;
import lib.dto.bill.TotalPriceDto;
import lib.service.ServiceOrderService;
import server.convert.autovehicle.PartConvertor;
import server.convert.autovehicle.ServiceOrderConvertor;
import server.dao.impl.autovehicle.*;
import server.dao.impl.client.ClientDaoImpl;
import server.dao.impl.user.UserDaoImpl;
import server.dao.interfaces.*;
import server.model.autovehicle.ServiceOrder;
import server.model.autovehicle.Vehicle;
import server.model.client.Client;
import server.model.user.User;

import javax.persistence.Persistence;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ServiceOrderServiceImpl extends UnicastRemoteObject implements ServiceOrderService {

    private ServiceOrderDao serviceOrderDao;
    private VehicleDao vehicleDao;
    private UserDao userDao;
    private ClientDao clientDao;
    private PartDao partDao;
    private CountPartDao countPartDao;


    public ServiceOrderServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("serviceAuto");
        var entityManager = entityManagerFactory.createEntityManager();

        serviceOrderDao = new ServiceOrderDaoImpl(entityManager);
        vehicleDao = new VehicleDaoImpl(entityManager);
        userDao = new UserDaoImpl(entityManager);
        clientDao = new ClientDaoImpl(entityManager);
        partDao = new PartDaoImpl(entityManager);
        countPartDao = new CountPartDaoImpl(entityManager);

    }

    @Override
    public boolean createServiceOrder(ServiceOrderDto serviceOrderDto) throws RemoteException{
        ServiceOrder serviceOrder = ServiceOrderConvertor.convert(serviceOrderDto);

        Client client = clientDao.findClientById(serviceOrderDto.getClientDto().getId());
        Vehicle vehicle = vehicleDao.findById(serviceOrderDto.getVehicleDtos().getId());

        User user = userDao.findByName(serviceOrderDto.getUserDto().getUserId().getUserName())
                                    .orElseThrow(NoSuchElementException::new);

        serviceOrder.setClient(client);
        serviceOrder.setVehicle(vehicle);
        serviceOrder.setUser(user);

        return serviceOrderDao.createServiceOrder(serviceOrder);
    }

    @Override
    public ServiceOrderDto findById(int id) throws RemoteException{
        return ServiceOrderConvertor
                    .convert(serviceOrderDao.findById(id));
    }

    @Override
    public Collection<ServiceOrderDto> findAll() throws RemoteException{
        return serviceOrderDao.findAll()
                .stream()
                .map(ServiceOrderConvertor::convert)
                .collect(Collectors.toList());

    }

    @Override
    public List<Integer> findAllServiceOrderIds() throws RemoteException{
        return serviceOrderDao.findAllServiceOrderIds();

    }

    @Override
    public boolean updateServiceOrder(ServiceOrderDto serviceOrderDto) throws RemoteException{

        ServiceOrder serviceOrder = serviceOrderDao.findById(serviceOrderDto.getId());



        var parts =serviceOrderDto.getPartsIds().stream()
                .map(s ->partDao.findPartById(s))
                .collect(Collectors.toList());

//        var countParts = serviceOrder.getCoutParts().stream()
//                .map(s->countPartDao.findById(s.getId()))
//                .collect(Collectors.toList());


                serviceOrder.setParts(parts);
            //    serviceOrder.setCoutParts(countParts);

            //    countParts.stream().forEach(s ->s.setServiceOrder(serviceOrder));



              return  serviceOrderDao.updateServiceOrder(serviceOrder);

    }

    @Override
    public int updateParsAndPartsCount(int orderId) throws RemoteException{
      return   serviceOrderDao.updateParsAndPartsCount(orderId);

    }

    //de sters
    @Override
    public List<Object[]> findOrdersByIds(int id) throws RemoteException{
//         serviceOrderDao.findOrdersByIds(id).stream()
//                .map(s -> Arrays.stream(s))
//                 .map(s ->s.)
        return null;
    }


    //de sters
    @Override
    public List<PartDto> initInfoOnPartPageAndCreateOrderPage(int orderId)throws RemoteException{

        Object x = serviceOrderDao.initInfoOnPartPageAndCreateOrderPage(orderId);
              ServiceOrder y = (ServiceOrder)  x;
               return y.getParts().stream().map(PartConvertor::convert)
                       .collect(Collectors.toList());

    }

    @Override
    public int setTotalPriceToOrder(int orderId, double totalPrice) throws RemoteException{
        return serviceOrderDao.setTotalPriceToOrder(orderId, totalPrice);
    }

    @Override
    public void makeBill(List<PartDto> partsDtos, String path, BillDto billDto, TotalPriceDto totalPriceDto) throws RemoteException{
        try(PrintStream ps = new PrintStream(path)) {

            ps.println(billDto);

            for (PartDto part : partsDtos) {
                ps.println(part.toString());
            }

            ps.println(totalPriceDto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateServiceOrderStatus(int orderId, Status status) throws RemoteException{
       return  serviceOrderDao.updateServiceOrderStatus(orderId, status);
    }
}
