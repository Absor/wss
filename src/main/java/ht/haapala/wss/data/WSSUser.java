package ht.haapala.wss.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Database relational class for users.
 *
 * @author Heikki Haapala
 */
@Entity
@Table(name = "WSSUser")
public class WSSUser implements Serializable {

    @Id
    @Column(name = "Username", nullable = false)
    @Length(min = 3)
    private String username;
    @Column(name = "Password", nullable = false)
    @JsonIgnore
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
    private String barePassword;
    @Transient
    private String id;

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

    /**
     * Unencoded password is given in this field.
     */
    public String getBarePassword() {
        return barePassword;
    }

    /**
     * Unencoded password is given in this field.
     */
    public void setBarePassword(String barePassword) {
        this.barePassword = barePassword;
    }

    /**
     * Same as username. For backbone to work nicely.
     */
    public String getId() {
        return id;
    }

    /**
     * Same as username. For backbone to work nicely.
     */
    public void setId(String id) {
        this.id = id;
    }
}
