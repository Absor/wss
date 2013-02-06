package ht.haapala.wss.data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Heikki Haapala
 */
@Entity
public class WSSUser {

    @Id
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
