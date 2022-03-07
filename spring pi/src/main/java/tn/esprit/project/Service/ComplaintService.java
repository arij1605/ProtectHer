package tn.esprit.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Complaint;
import tn.esprit.project.Entities.Topic;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.ComplaintRepository;
import tn.esprit.project.Repository.UserRepository;


import java.io.Console;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ComplaintService {

    @Autowired
    UserRepository ur;

    @Autowired
    ComplaintRepository cr;


    public Complaint add_complaint(Complaint c , Long Id_sender){
        User u = ur.findById(Id_sender).get();
        c.setUser(u);
        c.setDateComplaint(new Date());
        return cr.save(c);
    }

    public void Delete_complaint(Long Id_Complaint){
        cr.deleteById(Id_Complaint);
    }


    public List<Complaint> FilterByTopic(String topic){
        List<Complaint> complaintsBytopic = cr.findAll()
                .stream().filter(c->c.getTopic().equals(Topic.valueOf(topic)))
                .collect(Collectors.toList());
        return complaintsBytopic;

    }


    public Topic most_topic_complained(){
        AtomicInteger nbsecurity= new AtomicInteger();
        AtomicInteger nbservice= new AtomicInteger();
        AtomicInteger nbquality= new AtomicInteger();
        cr.findAll().forEach(c->{
            if(c.getTopic().equals(Topic.Service))
                nbservice.addAndGet(1);
            else if (c.getTopic().equals(Topic.Quality))
                nbquality.addAndGet(1);
            else
                nbsecurity.addAndGet(1);
        });
        if((nbquality.intValue()>nbsecurity.intValue()) && (nbquality.intValue()>nbservice.intValue()))
            return Topic.Quality;

        else if((nbservice.intValue()>nbsecurity.intValue()) && (nbservice.intValue()>nbquality.intValue()))
            return Topic.Service;
        else
            return Topic.Security;


    }

    public Map<String, Integer> triByUser(){
        Map<String, Integer> m  = new HashMap<String,Integer>();
        ur.findAll().forEach(u-> {
            m.put(u.getFirstName()+" "+u.getLastName(),u.getComplaints().size());
        });
        return m.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

}
