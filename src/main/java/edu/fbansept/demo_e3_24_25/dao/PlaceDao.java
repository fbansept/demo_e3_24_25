package edu.fbansept.demo_e3_24_25.dao;

import edu.fbansept.demo_e3_24_25.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceDao extends JpaRepository<Place, Integer> {
}
