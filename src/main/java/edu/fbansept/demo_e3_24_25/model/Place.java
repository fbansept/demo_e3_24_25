package edu.fbansept.demo_e3_24_25.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demo_e3_24_25.view.AffichageReservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @JsonView(AffichageReservation.class)
    protected String numero;

    @OneToMany(mappedBy = "place")
    protected List<Reservation> reservations;
}
