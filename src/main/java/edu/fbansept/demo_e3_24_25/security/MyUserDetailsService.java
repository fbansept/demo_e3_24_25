package edu.fbansept.demo_e3_24_25.security;

import edu.fbansept.demo_e3_24_25.dao.UtilisateurDao;
import edu.fbansept.demo_e3_24_25.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurDao
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur inexistant"));

        return new MyUserDetails(utilisateur);
    }
}
