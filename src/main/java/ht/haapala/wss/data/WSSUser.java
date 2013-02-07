package ht.haapala.wss.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Heikki Haapala
 */
@Entity
@Table(name = "WSSUser")
public class WSSUser implements Serializable {
    @Id
    private Long id;

    @Column(name = "Username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
