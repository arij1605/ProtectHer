package com.esprit.ProtectHer.Service;


import com.esprit.ProtectHer.entity.Chat;

import java.util.List;

public interface IChat {
    Chat addChat(Chat m);

    List<Chat> retrieveAllChat();
    void deleteChat(Long id);
    Chat updateChat(Chat m);

}
