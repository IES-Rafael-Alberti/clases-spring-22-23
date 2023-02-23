package es.iesrafaelalberti.clasesspring2223.repositories;

import es.iesrafaelalberti.clasesspring2223.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}