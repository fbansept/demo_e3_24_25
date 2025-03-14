package edu.fbansept.demo_e3_24_25.dao;

import edu.fbansept.demo_e3_24_25.model.Place;
import edu.fbansept.demo_e3_24_25.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByEmail(String email);
}
