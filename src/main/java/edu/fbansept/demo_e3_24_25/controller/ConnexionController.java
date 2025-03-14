package edu.fbansept.demo_e3_24_25.controller;

import edu.fbansept.demo_e3_24_25.dao.UtilisateurDao;
import edu.fbansept.demo_e3_24_25.model.Utilisateur;
import edu.fbansept.demo_e3_24_25.security.JwtUtils;
import edu.fbansept.demo_e3_24_25.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ConnexionController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @PostMapping("/inscription")
    public ResponseEntity<Utilisateur> inscription(@RequestBody Utilisateur utilisateur) {

        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));

        utilisateurDao.save(utilisateur);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody Utilisateur utilisateur) {

        try {
            MyUserDetails userDetails = (MyUserDetails) authenticationProvider
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            utilisateur.getEmail(),
                            utilisateur.getPassword()))
                    .getPrincipal();

            return new ResponseEntity<>(jwtUtils.generateJwt(userDetails), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
