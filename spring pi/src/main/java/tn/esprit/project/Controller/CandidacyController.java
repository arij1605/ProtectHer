package tn.esprit.project.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Candidacy;
import tn.esprit.project.Entities.Offre;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Service.CandidacyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Candidacy")
public class CandidacyController {

    @Autowired
    CandidacyService cs;

    @GetMapping("/filter/{dn}")
    @ResponseBody
    public List<Offre> filteringcandidatebyDomain (@PathVariable("dn") String domain){
    return cs.filteringcandidatebyDomain(domain);
    }

    @PostMapping("/apply/{iduser}/{idoffer}")
    @ResponseBody
    public void ApplyForAJob (@RequestBody Candidacy c , @PathVariable("iduser") Long idUser, @PathVariable("idoffer") Long idoffrer){
    cs.ApplyForAJob(c,idUser,idoffrer);
    }
}
