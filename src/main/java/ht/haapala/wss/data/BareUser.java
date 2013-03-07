package ht.haapala.wss.data;

/**
 * Helper class for hiding information when outputing JSON data. Only shows
 * username.
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
