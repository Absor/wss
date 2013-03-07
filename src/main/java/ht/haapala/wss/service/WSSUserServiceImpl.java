package ht.haapala.wss.service;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WSSUser;
import ht.haapala.wss.repository.PlannedShiftRepository;
import ht.haapala.wss.repository.WSSUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for users.
 *
 * @author Heikki Haapala
 */
@Service
public class WSSUserServiceImpl implements WSSUserService {

    @Autowired
    WSSUserRepository userRepository;
    @Autowired
    PlannedShiftRepository plannedShiftRepository;

    /**
     * Handles transactions to find a user by username.
     * 
     * @param username given username
     * @return user by the given username
     */
    @Override
    @Transactional(readOnly = true)
    public WSSUser findOne(String username) {
        WSSUser findOne = userRepository.findOne(username);
        if (findOne != null) {
            findOne.setId(findOne.getUsername());
        }
        return findOne;
    }

    /**
     * Handles transactions to find all users.
     *
     * @return a list of all users
     */
    @Override
    @Transactional(readOnly = true)
    public List<WSSUser> findAll() {
        List<WSSUser> findAll = userRepository.findAll();
        if (findAll != null) {
            for (WSSUser user : findAll) {
                user.setId(user.getUsername());
            }
        }
        return findAll;
    }

    /**
     * Handles transactions to create a new user.
     * 
     * @param user user data
     * @return saved user
     */
    @Override
    @Transactional(readOnly = false)
    public WSSUser save(WSSUser user) {
        WSSUser save = userRepository.save(user);
        if (save != null) {
            save.setId(save.getUsername());
        }
        return save;
    }

    /**
     * Handles transactions to delete user by username.
     * 
     * @param username user to be deleted
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String username) {
        WSSUser user = userRepository.findOne(username);
        // deletes also planned shifts that the user has
        if (user != null) {
            List<PlannedShift> shifts = plannedShiftRepository.findByEmployee(user);
            if (shifts != null) {
                for (PlannedShift shift : shifts) {
                    plannedShiftRepository.delete(shift.getId());
                }
            }
            userRepository.delete(username);
        }
    }
}
