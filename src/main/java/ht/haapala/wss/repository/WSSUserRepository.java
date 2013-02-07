package ht.haapala.wss.repository;

import ht.haapala.wss.data.WSSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Heikki Haapala
 */
@Repository
public interface WSSUserRepository extends JpaRepository<WSSUser, Long> {

    public WSSUser findByUsername(String username);
    
}
