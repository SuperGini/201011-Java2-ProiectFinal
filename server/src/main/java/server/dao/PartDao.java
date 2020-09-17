package server.dao;

import server.model.autovehicle.Part;

import java.util.Collection;
import java.util.Optional;

public interface PartDao {
    boolean createPart(Part part);

    Part findPartById(int id);

    Optional<Part> findPartByName(String partName);

    Collection<Part> findAllParts();
}
