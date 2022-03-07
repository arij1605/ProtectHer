package tn.esprit.project.Entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
@Table(name = "Complaint")
public class Complaint implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdComplaint;
    @Enumerated(EnumType.STRING)
    private Topic topic;
    private String MessageComplaint;
    private String ImageComplaint;
    @Temporal(TemporalType.DATE)
    private Date DateComplaint;
    private boolean Cloture = false;

    @JsonIgnore
    @ManyToOne()
    User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="complaint")
    @JsonIgnore
    private Set<ResponseComplaint> responsesComplaints;
}
