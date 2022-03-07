package tn.esprit.project.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Complaint;
import tn.esprit.project.Entities.Topic;
import tn.esprit.project.Service.ComplaintService;


import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Complaint")
public class ComplaintController {

    @Autowired
    ComplaintService cs;

    @ResponseBody
    @PostMapping("/add/{id}")
    public Complaint add_complaint(@RequestBody Complaint c , @PathVariable("id") Long Id_sender){
        return cs.add_complaint(c, Id_sender);
    }


    @DeleteMapping("/delete/{id}")
    public void delete_complaint(@PathVariable("id") Long id_Complaint){
        cs.Delete_complaint(id_Complaint);
    }

    @GetMapping("/filtertopic/{topic}")
    public List<Complaint> FilterByTopic(@PathVariable("topic") String topic){
        return cs.FilterByTopic(topic);
    }

    @GetMapping("/most_topic")
    @ResponseBody
    public Topic most_topic_complained(){
        return cs.most_topic_complained();
    }

    @GetMapping("/triByUser")
    @ResponseBody
    public Map<String, Integer> triByUser(){
        return cs.triByUser();
    }

}