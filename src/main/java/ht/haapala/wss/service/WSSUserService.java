package ht.haapala.wss.service;

import ht.haapala.wss.data.WSSUser;
import java.util.List;

/**
 *
 * @author Heikki Haapala
 */
public interface WSSUserService {

    WSSUser findOne(String username);

    List<WSSUser> findAll();

    WSSUser save(WSSUser user);

    void delete(String username);
}
