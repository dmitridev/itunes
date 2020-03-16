package org.astelit.itunes.repository;

import org.astelit.itunes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginAndPassword(String login, String password);
}
