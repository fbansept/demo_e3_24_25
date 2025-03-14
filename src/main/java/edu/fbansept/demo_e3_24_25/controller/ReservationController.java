package edu.fbansept.demo_e3_24_25.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demo_e3_24_25.dao.ReservationDao;
import edu.fbansept.demo_e3_24_25.model.Reservation;
import edu.fbansept.demo_e3_24_25.model.Utilisateur;
import edu.fbansept.demo_e3_24_25.security.IsEmploye;
import edu.fbansept.demo_e3_24_25.security.IsPartenaire;
import edu.fbansept.demo_e3_24_25.security.MyUserDetails;
import edu.fbansept.demo_e3_24_25.view.AffichageReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ReservationController {

    @Autowired
    protected ReservationDao reservationDao;

    @GetMapping("/reservation/{id}")
    @JsonView(AffichageReservation.class)
    @IsEmploye
    public ResponseEntity<Reservation> get(@PathVariable int id) {

        Optional<Reservation> optionalReservation = reservationDao.findById(id);

        if (optionalReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalReservation.get(), HttpStatus.OK);

    }

    @GetMapping("/reservations")
    @JsonView(AffichageReservation.class)
    @IsEmploye
    public List<Reservation> getAll() {

        return reservationDao.findAll();
    }

    @GetMapping("/reservation-at-date/{date}")
    @JsonView(AffichageReservation.class)
    @IsEmploye
    public List<Reservation> getAllByDate(@PathVariable String date) {

        LocalDateTime dateTime = LocalDateTime.parse(date);

        return reservationDao.findAllByDateDebutLessThanEqualAndDateFinGreaterThanEqual(
                dateTime,dateTime
        );
    }

    @PostMapping("/reservation")
    @JsonView(AffichageReservation.class)
    @IsEmploye
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) {

        reservation.setId(null);

        reservationDao.save(reservation);

        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @PostMapping("/reservation-place-journee")
    @JsonView(AffichageReservation.class)
    @IsPartenaire
    public ResponseEntity<Reservation> createReservationJournee(
            @RequestBody Reservation reservation,
            @AuthenticationPrincipal MyUserDetails userDetails
    ) {

        reservation.setId(null);

        LocalDateTime dateDebut = LocalDateTime.now()
                .withHour(8).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime dateFin = dateDebut.withHour(19);

        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);

        reservation.setUtilisateur(userDetails.getUtilisateur());

        reservation.setDateCreation(LocalDateTime.now());

        reservationDao.save(reservation);

        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }


    @PutMapping("/reservation/{id}")
    @IsPartenaire
    public ResponseEntity<Reservation> update(
            @AuthenticationPrincipal MyUserDetails userDetails,
            @RequestBody Reservation reservation,
            @PathVariable int id) {

        reservation.setId(id);

        Optional<Reservation> optionalReservation = reservationDao.findById(id);

        if (optionalReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Reservation reservationBdd = optionalReservation.get();

        //si l'email de l'utilisateur connecté est différent de l'email de la personne
        //qui a créé cette reservation, sauf si c'est un admin
        if(!reservationBdd.getUtilisateur().getRole().equals("ROLE_ADMIN") &&
                !reservationBdd.getUtilisateur().getEmail().equals(userDetails.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        reservationDao.save(reservation);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/reservation/{id}")
    @IsPartenaire
    public ResponseEntity<Reservation> delete(
            @PathVariable int id,
            @AuthenticationPrincipal MyUserDetails userDetails) {

        Optional<Reservation> optionalReservation = reservationDao.findById(id);

        if (optionalReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Reservation reservationBdd = optionalReservation.get();

        //si l'email de l'utilisateur connecté est différent de l'email de la personne
        //qui a créé cette reservation, sauf si c'est un admin
        if(!reservationBdd.getUtilisateur().getRole().equals("ROLE_ADMIN") &&
                !reservationBdd.getUtilisateur().getEmail().equals(userDetails.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        reservationDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
