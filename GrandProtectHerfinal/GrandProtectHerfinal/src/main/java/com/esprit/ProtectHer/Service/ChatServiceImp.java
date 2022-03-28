package com.esprit.ProtectHer.Service;



import com.esprit.ProtectHer.entity.Chat;
import com.esprit.ProtectHer.Repository.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImp implements IChat {

    @Autowired
    ChatRepo chatRepo;
    @Override
    public Chat addChat(Chat m) {

        return chatRepo.save(m);
    }
    @Override
    public List<Chat> retrieveAllChat() {
        // TODO Auto-generated method stub
        List<Chat> Chats= (List<Chat>) chatRepo.findAll();
        return Chats;
    }

    @Override
    public void deleteChat(Long id){
        chatRepo.findById(id);
    }
    @Override
    public Chat updateChat(Chat m) {
        // TODO Auto-generated method stub
        return chatRepo.save(m);
    }




    }
