package com.esprit.ProtectHer.Service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.ProtectHer.Repository.DisLikeRepo;
import com.esprit.ProtectHer.Repository.LikeRepo;
import com.esprit.ProtectHer.Repository.PostRepo;
import com.esprit.ProtectHer.entity.Post;
import com.esprit.ProtectHer.entity.PostDislike;
import com.esprit.ProtectHer.entity.PostLike;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class PostServiceImp implements IPost{
    @Autowired
    PostRepo postRepo;
    @Autowired
    LikeRepo LikeRepo;
    @Autowired
    DisLikeRepo DislikeRepo;
   
    @Override
    public Post addPost(Post p) {

        return postRepo.save(p);
    }
    
    @Override
	public void addLike(Long idPost) {
		PostLike lk = new PostLike();
		Post publication = postRepo.findById(idPost).orElse(null);
		
		
		lk.setPublication(publication);
		
		
			LikeRepo.save(lk);
		
		
		
	}
    @Override
   	public void addDislike(Long idPost) {
   		PostDislike lk = new PostDislike();
   		Post publication = postRepo.findById(idPost).orElse(null);
   		
   		
   		lk.setPublication(publication);
   		
   		
   		DislikeRepo.save(lk);
   		
   		
   		
   	}
    
    
    @Override
    public List<Post> retrieveAllPosts() {
        // TODO Auto-generated method stub
        List<Post> Posts = (List<Post>) postRepo.findAll();
        return Posts;
    }
    @Override
    public Post retrievePost(Long id) {
        // TODO Auto-generated method stub
        Post post = postRepo.findById(id).orElse(null);
        return post;
    }
    @Override
    public void deletePost(Long id ) {
        postRepo.deleteById(id);
    }
    @Override
    public Post updatePost(Post p) {
        // TODO Auto-generated method stub
        return postRepo.save(p);
    }


}
