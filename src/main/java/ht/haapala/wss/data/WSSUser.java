package ht.haapala.wss.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Heikki Haapala
 */
@Entity
@Table(name = "WSSUser")
public class WSSUser implements Serializable {

    @Id
    @Column(name = "Username", nullable = false)
    private String username;
    @Column(name = "Password")
    @JsonIgnore
    private String password;
    @Column(name = "Enabled", nullable = false)
    private boolean enabled;
    @Column(name = "UserRole", nullable = false)
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
