package com.esprit.ProtectHer.Service;



import com.esprit.ProtectHer.entity.Post;

import java.util.List;

public interface IPost {
    Post addPost(Post P);
    List<Post> retrieveAllPosts();
    Post retrievePost(Long id);
    void deletePost(Long id);
    Post updatePost(Post P);
    public void addLike(Long idPost);
	public void addDislike(Long idPost);
}
