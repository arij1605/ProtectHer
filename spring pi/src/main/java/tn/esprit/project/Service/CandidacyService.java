package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Candidacy;
import tn.esprit.project.Entities.Domain;
import tn.esprit.project.Entities.Offre;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.CandidacyRepository;
import tn.esprit.project.Repository.OffreRepository;
import tn.esprit.project.Repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidacyService {

    @Autowired
    CandidacyRepository cr ;
    @Autowired
    UserRepository ur ;
    @Autowired
    OffreRepository or ;

    /************** user area ************/

    //filtering candidate by Domain

    public List <Offre> filteringcandidatebyDomain (String domain){
        List<Offre> offres = or.findAll();
        return offres.stream().filter(e->e.getDomain().equals(Domain.valueOf(domain))).collect(Collectors.toList());
    }

    //Apply For A Job

    public void ApplyForAJob (Candidacy c , Long idUser,Long idoffer){
        Offre offer = or.findById(idoffer).get();
        User user = ur.findById(idUser).get();
        c.setCandidacyUser(user);
        c.setOffer(offer);
        cr.save(c);
    }



}
