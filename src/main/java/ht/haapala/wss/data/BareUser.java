package ht.haapala.wss.data;

/**
 *
 * @author Heikki Haapala
 */
public class BareUser {
    
    private String username;

    public BareUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
