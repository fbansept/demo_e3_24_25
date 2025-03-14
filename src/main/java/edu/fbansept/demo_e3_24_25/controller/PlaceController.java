package edu.fbansept.demo_e3_24_25.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demo_e3_24_25.dao.PlaceDao;
import edu.fbansept.demo_e3_24_25.model.Place;
import edu.fbansept.demo_e3_24_25.security.IsAdmin;
import edu.fbansept.demo_e3_24_25.security.IsPartenaire;
import edu.fbansept.demo_e3_24_25.view.AffichagePlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PlaceController {

    @Autowired
    protected PlaceDao placeDao;

    @GetMapping("/place/{id}")
    @JsonView(AffichagePlace.class)
    @IsPartenaire
    public ResponseEntity<Place> get(@PathVariable int id) {

        Optional<Place> optionalPlace = placeDao.findById(id);

        if (optionalPlace.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalPlace.get(), HttpStatus.OK);

    }

    @GetMapping("/places")
    @JsonView(AffichagePlace.class)
    @IsPartenaire
    public List<Place> getAll() {

        return placeDao.findAll();
    }

    @GetMapping("/place-indisponible/{date}")
    @JsonView(AffichagePlace.class)
    @IsPartenaire
    public List<Place> getAllIndiponible(@PathVariable String date) {

        LocalDateTime dateTime = LocalDateTime.parse(date);

        return placeDao.indisponibleLe(dateTime);
    }

    @GetMapping("/place-disponible/{date}")
    @JsonView(AffichagePlace.class)
    @IsPartenaire
    public List<Place> getAllDisponible(@PathVariable String date) {

        LocalDateTime dateTime = LocalDateTime.parse(date);

        return placeDao.disponibleLe(dateTime);
    }

    @GetMapping("/place-by-numero/{numero}")
    @JsonView(AffichagePlace.class)
    @IsPartenaire
    public ResponseEntity<Place> getByNumero(@PathVariable String numero) {

        Optional<Place> optionalPlace = placeDao.findByNumero(numero);

        if (optionalPlace.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalPlace.get(), HttpStatus.OK);

    }

    @PostMapping("/place")
    @JsonView(AffichagePlace.class)
    @IsAdmin
    public ResponseEntity<Place> create(@RequestBody Place place) {

        place.setId(null);

        placeDao.save(place);

        return new ResponseEntity<>(place, HttpStatus.CREATED);
    }

    @PutMapping("/place/{id}")
    @IsAdmin
    public ResponseEntity<Place> update(
            @RequestBody Place place,
            @PathVariable int id) {

        place.setId(id);

        Optional<Place> optionalPlace = placeDao.findById(id);

        if (optionalPlace.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        placeDao.save(place);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/place/{id}")
    @IsAdmin
    public ResponseEntity<Place> delete(@PathVariable int id) {

        Optional<Place> optionalPlace = placeDao.findById(id);

        if (optionalPlace.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        placeDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
