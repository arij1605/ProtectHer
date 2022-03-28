package com.esprit.ProtectHer.Controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.esprit.ProtectHer.Service.IComment;
import com.esprit.ProtectHer.Service.IPost;
import com.esprit.ProtectHer.entity.Comment;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentRestControler {
    @Autowired
    IComment commentService;
    @Autowired
    IPost postService;

    @GetMapping("/retrieve-all-comment")
    @ResponseBody
    public List<Comment> getComment() {
        List<Comment> listComments = commentService.retrieveAllComments();
        return listComments;
    }

  
    @PostMapping("/add-comment/{idPost}")
    @ResponseBody
    public String addComment(@RequestBody Comment c,@PathVariable("idPost") Long idPost) {
        return  commentService.addComment(c,idPost);
 
        
        
    }




    }
