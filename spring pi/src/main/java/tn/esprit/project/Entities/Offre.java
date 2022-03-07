package tn.esprit.project.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Offre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long IdOffre;
    String description;
    @Temporal(TemporalType.TIMESTAMP)
    Date CreateAt ;
    String locality;
    @Enumerated(EnumType.STRING)
    Domain domain;



@JsonIgnore
    @ManyToOne
    User userCreator ;
    @OneToMany(mappedBy = "offer")
    List<Candidacy> candidacies ;


}
