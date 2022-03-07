package tn.esprit.project.Entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "User")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdUser;
    private String FirstName;
    private String LastName;



    /************Relation**************/

    @OneToOne()
    @JsonIgnore
    private ResponseComplaint response;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonIgnore
    private Set<Complaint> complaints;
    @OneToMany(mappedBy="userCreator")
    List<Offre> offres;

    @OneToMany(mappedBy="candidacyUser")
    List<Candidacy> candidacies;


}
