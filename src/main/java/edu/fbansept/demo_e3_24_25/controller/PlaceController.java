package edu.fbansept.demo_e3_24_25.controller;

import edu.fbansept.demo_e3_24_25.dao.PlaceDao;
import edu.fbansept.demo_e3_24_25.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlaceController {

    @Autowired
    protected PlaceDao placeDao;

    @GetMapping("/place/{id}")
    public ResponseEntity<Place> get(@PathVariable int id) {

        Optional<Place> optionalPlace = placeDao.findById(id);

        if (optionalPlace.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalPlace.get(), HttpStatus.OK);

    }

    @GetMapping("/places")
    public List<Place> getAll() {

        return placeDao.findAll();
    }

    @PostMapping("/place")
    public ResponseEntity<Place> create(@RequestBody Place place) {

        place.setId(null);

        placeDao.save(place);

        return new ResponseEntity<>(place, HttpStatus.CREATED);
    }

    @PutMapping("/place/{id}")
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
    public ResponseEntity<Place> delete(@PathVariable int id) {

        Optional<Place> optionalPlace = placeDao.findById(id);

        if (optionalPlace.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        placeDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
