package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Offre;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.OffreRepository;
import tn.esprit.project.Repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OffreService {

    @Autowired
    UserRepository ur ;
    @Autowired
    OffreRepository or ;

    /************** company will create offers. ************/

    //add
    public Offre AddOffer(Offre f , Long idUser){
        Timestamp CreateDate =  new Timestamp(System.currentTimeMillis());
        User user = ur.findById(idUser).get();
        f.setCreateAt(CreateDate);
        f.setUserCreator(user);
        return or.save(f) ;
    }

    //Delete
    public void DeleteOffer (Long idOffer){
        Offre offer = or.findById(idOffer).get();
        or.delete(offer);
    }

    //update
    public Offre UpdateOffer(Long idOffer,Offre offre){
        Offre offerCible = or.findById(idOffer).get();
        offerCible.setDescription(offre.getDescription());
    return or.save(offerCible);
    }

    //getOffers By User(company)
    public List<Offre> GetOffersByUser(Long idUser){
        User user = ur.findById(idUser).get();
        return user.getOffres();
    }

    //filtering candidate by Date
    public List<User> filteringCandidateBy(Long idUser){
        return null;
    }

}
