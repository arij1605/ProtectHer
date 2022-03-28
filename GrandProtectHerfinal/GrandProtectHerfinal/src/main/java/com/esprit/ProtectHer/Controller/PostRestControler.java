package com.esprit.ProtectHer.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.ProtectHer.Repository.PostRepo;
import com.esprit.ProtectHer.Service.IPost;
import com.esprit.ProtectHer.entity.Post;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;


@RestController
@RequestMapping("/post")
public class PostRestControler {
    @Autowired
    IPost postService;
    @Autowired
    PostRepo postRepo;
    
    
    @PostMapping("/add-post")
    @ResponseBody
    public Post addPost(@RequestBody Post p) {
      Post post=   postService.addPost(p);
        return post;

    }

    @GetMapping("/retrieve-post/{post-id}")
    @ResponseBody
    public Post retrievePost(@PathVariable("post-id") Long postId) {

        return postService.retrievePost(postId);
    }
    @DeleteMapping("/delete-post/{post-id}")
    @ResponseBody
    public void deletePost(@PathVariable("post-id") Long PostId) {

        postService.deletePost(PostId);
    }
    @PutMapping("/update-post")
    @ResponseBody
    public Post updatePost(@RequestBody Post post) {

        return postService.updatePost(post);
    }

    @PutMapping("/uploadPictureToPost")
    public Post uploadPictureToPost(@RequestParam Long postId,
                                    @RequestPart("file") MultipartFile file) {
        try {
            Post post = postRepo.findById(postId).orElse(null);
            if (post != null) {
                File directory = new File("upload//");
                if (!directory.exists())
                    directory.mkdir();
                byte[] bytes = new byte[0];
                bytes = file.getBytes();
                Files.write(Paths.get("upload//" + file.getOriginalFilename()), bytes);
               post.setPicturepath(Paths.get("upload//" + file.getOriginalFilename()).toString());
                return postRepo.save(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @PutMapping("/addlike/{idPub}")
    public void addLike(@PathVariable("idPub") Long idPub) {
         postService.addLike(idPub);
    
    }  
   
    @PutMapping("/adddislike/{idPub}")
    public void adddislike(@PathVariable("idPub") Long idPub) {
         postService.addDislike(idPub);
    
    }  
    

}
