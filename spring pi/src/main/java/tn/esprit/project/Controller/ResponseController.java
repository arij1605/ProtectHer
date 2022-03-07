package tn.esprit.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.ResponseComplaint;
import tn.esprit.project.Service.ResponseService;


import java.util.List;

@RestController
@RequestMapping("/ResponseComplaint")
public class ResponseController {

    @Autowired
    ResponseService rs;


    @PostMapping("/add/{id_complaint}/{id_user}")
    @ResponseBody
    public ResponseComplaint add_response (@PathVariable("id_complaint") Long id_complaint
            , @PathVariable("id_user") Long id_user, @RequestBody ResponseComplaint r){
        return rs.add_response(id_complaint, id_user, r);
    }
    @DeleteMapping("/delete/{id_response}")
    @ResponseBody
    public void delete_response(@PathVariable("id_response") Long id_response){
        rs.delete_response(id_response);
    }

    @GetMapping("/By/{id_user}")
    public List<ResponseComplaint> FilterByUser(@PathVariable("id_user") Long id_user){
        return rs.FilterByUser(id_user);
    }


}