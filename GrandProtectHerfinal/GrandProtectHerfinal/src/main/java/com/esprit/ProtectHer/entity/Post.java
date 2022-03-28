package com.esprit.ProtectHer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    private String message;
   private String picture;
    private String picturepath;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="publication")
	@JsonIgnore
	private Set<PostLike> likes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="publication")
	@JsonIgnore
	private Set<PostDislike> dislikes;

    @ManyToOne
    @JsonIgnore
    User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="publications")
	@JsonIgnore
	private Set<Comment> comments;




}
