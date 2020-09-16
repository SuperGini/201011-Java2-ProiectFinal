package server.dao.impl;

import server.model.autovehicle.Part;

import java.util.Collection;
import java.util.Optional;

public interface PartsDao {
    boolean createPart(Part part);

    Part findPartById(int id);

    Optional<Part> findPartByName(String partName);

    Collection<Part> findAllParts();
}
