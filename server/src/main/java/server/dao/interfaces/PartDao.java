package server.dao.interfaces;

import server.model.autovehicle.Part;

import java.util.Collection;
import java.util.Optional;

public interface PartDao {
    boolean createPart(Part part);

    Part findPartById(int id);

    void refreshPart(Part part);

    Optional<Part> findPartByName(String partName);

//    int increasePartCount(int count, String partName);

 //   int decreasePartCount(int count, String partName);

    Collection<Part> findAllParts();
}
