package com.esprit.ProtectHer.Repository;

import com.esprit.ProtectHer.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepo extends JpaRepository<Chat,Long> {
}
