package edu.fbansept.demo_e3_24_25.controller;

import edu.fbansept.demo_e3_24_25.dao.ReservationDao;
import edu.fbansept.demo_e3_24_25.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {

    @Autowired
    protected ReservationDao reservationDao;

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> get(@PathVariable int id) {

        Optional<Reservation> optionalReservation = reservationDao.findById(id);

        if (optionalReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalReservation.get(), HttpStatus.OK);

    }

    @GetMapping("/reservations")
    public List<Reservation> getAll() {

        return reservationDao.findAll();
    }

    @GetMapping("/reservation-at-date/{date}")
    public List<Reservation> getAllByDate(@PathVariable String date) {

        LocalDateTime dateTime = LocalDateTime.parse(date);

        return reservationDao.findAllByDateDebutLessThanEqualAndDateFinGreaterThanEqual(
                dateTime,dateTime
        );
    }

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) {

        reservation.setId(null);

        reservationDao.save(reservation);

        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }


    @PutMapping("/reservation/{id}")
    public ResponseEntity<Reservation> update(
            @RequestBody Reservation reservation,
            @PathVariable int id) {

        reservation.setId(id);

        Optional<Reservation> optionalReservation = reservationDao.findById(id);

        if (optionalReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reservationDao.save(reservation);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Reservation> delete(@PathVariable int id) {

        Optional<Reservation> optionalReservation = reservationDao.findById(id);

        if (optionalReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reservationDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
