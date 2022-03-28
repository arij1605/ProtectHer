package com.esprit.ProtectHer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Setter
@Getter
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idChat;
    private Date sendDate;
    private Date dateofreceipt;
    private String Contents;
    private String Destination;
    private String sender;

    @ManyToOne
    User user;

}
