package tn.esprit.project.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseComplaint implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdReponseComplaint;
    private String Response;
    private String ImageReponseComplaint;
    @Temporal(TemporalType.DATE)
    private Date DateReponseComplaint;

    @JsonIgnore
    @OneToOne()
    User user;
    @JsonIgnore
    @ManyToOne()
    Complaint complaint;
}
