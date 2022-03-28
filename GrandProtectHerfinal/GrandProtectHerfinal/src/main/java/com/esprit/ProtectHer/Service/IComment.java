package com.esprit.ProtectHer.Service;


import com.esprit.ProtectHer.entity.Comment;

import java.util.List;

public interface IComment {
	 public String addComment(Comment c,Long idpub);
    List<Comment> retrieveAllComments();
    public int BadWordFilter(Comment c);
    
}
