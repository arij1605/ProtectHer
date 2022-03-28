package com.esprit.ProtectHer.Service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.ProtectHer.Repository.BadWordRepo;
import com.esprit.ProtectHer.Repository.CommentRepo;
import com.esprit.ProtectHer.Repository.PostRepo;
import com.esprit.ProtectHer.entity.Comment;
import com.esprit.ProtectHer.entity.Post;
import com.esprit.ProtectHer.entity.badword;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class CommentServiceImp implements IComment {
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    BadWordRepo badwordRepo;
    
    @Autowired
    PostRepo postRepo;

  @Override
    public String addComment(Comment c,Long idpub) {
    	Post pub = postRepo.findById(idpub).orElse(null);
    	c.setPublications(pub);
    	List<badword> badwords = badwordRepo.findAll();
    	List<String> badwords1 = new ArrayList<String>();
    	for (badword bd : badwords) {
			badwords1.add(bd.getWord());
		}
    	if (BadWordFilter(c) == 1) {
			commentRepo.save(c);
			return "no bad word found";
		}
    	else if (BadWordFilter(c) == 0) {
    		c.setText("*****");
    		commentRepo.save(c);
    		return "bad word found";
    	}
    	else {
    		commentRepo.save(c);
    return " no bad word found 2";
    	}
    	
        
    }
    
    @Override
	public int BadWordFilter(Comment c) {

		for (badword d : badwordRepo.findAll()) {

			if (c.getText().toLowerCase().contains(d.getWord().toLowerCase()) || c.getText() == null
					|| c.getText().length() == 0) {
				return 0;
			} else {
				return 1;
			}

		}
		return 4;

	}
    
    @Override
    public List<Comment> retrieveAllComments() {
        // TODO Auto-generated method stub
        List<Comment>Comments = (List<Comment>) commentRepo.findAll();
        return Comments;
    }
}

