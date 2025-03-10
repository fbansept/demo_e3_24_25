package edu.fbansept.demo_e3_24_25.dao;

import edu.fbansept.demo_e3_24_25.model.Place;
import edu.fbansept.demo_e3_24_25.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByDateDebutLessThanEqualAndDateFinGreaterThanEqual(
            LocalDateTime debut, LocalDateTime fin);
}
