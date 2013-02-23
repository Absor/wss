package ht.haapala.wss.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

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
    @Column(name = "Password", nullable = false)
    @JsonIgnore
    @NotBlank
    private String password;
    @Column(name = "Enabled", nullable = false)
    @NotNull
    private boolean enabled;
    @Column(name = "UserRole", nullable = false)
    @NotBlank
    private String role;
    @Column(name = "Email")
    private String email;
    @Transient
    private boolean changingPassword;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isChangingPassword() {
        return changingPassword;
    }

    public void setChangingPassword(boolean changingPassword) {
        this.changingPassword = changingPassword;
    }
}
