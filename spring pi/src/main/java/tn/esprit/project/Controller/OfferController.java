package tn.esprit.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Offre;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Service.OffreService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {


@Autowired
    OffreService os ;


    //add
    @GetMapping("/add/{iduser}")
    @ResponseBody
    public Offre AddOffer(@RequestBody Offre f ,@PathVariable("iduser") Long idUser){
        return os.AddOffer(f,idUser);
    }

    //Delete
    @DeleteMapping("/delete/{offer}")
    public void DeleteOffer (@PathVariable("offer") Long idOffer){
        os.DeleteOffer(idOffer);
    }

    //update
    @GetMapping("/update/{idoffer}")
    public Offre UpdateOffer(@PathVariable("idoffer")Long idOffer,@RequestBody Offre offre){

        return os.UpdateOffer(idOffer,offre);
    }

    //getOffers By User(company)
    @GetMapping("/get/{iduser}")
    @ResponseBody
    public List<Offre> GetOffersByUser(@PathVariable("iduser") Long idUser){
        return os.GetOffersByUser(idUser);
    }
}
