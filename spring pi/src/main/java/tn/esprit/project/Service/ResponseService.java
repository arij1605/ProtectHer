package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Complaint;
import tn.esprit.project.Entities.ResponseComplaint;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.ComplaintRepository;
import tn.esprit.project.Repository.ResponseRepository;
import tn.esprit.project.Repository.UserRepository;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {

    @Autowired
    ResponseRepository rr;


    @Autowired
    UserRepository ur;

    @Autowired
    ComplaintRepository cr;
    public ResponseComplaint add_response (Long id_complaint , Long id_user, ResponseComplaint r){
        User u =ur.findById(id_user).get();
        Complaint c = cr.findById(id_complaint).get();
        r.setComplaint(c);
        r.setUser(u);
        r.setDateReponseComplaint(new Date());
        return rr.save(r);
    }

    public void delete_response(Long id_response){
        rr.deleteById(id_response);
    }

    public List<ResponseComplaint> FilterByUser(Long id_user){
        User u = ur.findById(id_user).get();
        List<ResponseComplaint> responsesByuser = rr.findAll()
                .stream().filter(c->c.getUser().equals(u))
                .collect(Collectors.toList());
        return responsesByuser;
    }
}
