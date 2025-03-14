package edu.fbansept.demo_e3_24_25.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    public String generateJwt(MyUserDetails userDetails) {

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"azerty123")
                .setSubject(userDetails.getUsername())
                .compact();
    }

    public String getSubject(String jwt) {

        return Jwts.parser()
                .setSigningKey("azerty123")
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();

    }

}
