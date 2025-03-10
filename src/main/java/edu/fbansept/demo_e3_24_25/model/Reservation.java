package edu.fbansept.demo_e3_24_25.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected LocalDateTime dateDebut;
    protected LocalDateTime dateFin;

    @CreatedDate
    protected LocalDateTime dateCreation;

    @ManyToOne(optional = false)
    @JsonIgnore
    protected Place place;

    @ManyToOne(optional = false)
    protected Utilisateur utilisateur;
}
