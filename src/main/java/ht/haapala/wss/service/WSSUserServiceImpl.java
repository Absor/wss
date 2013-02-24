package ht.haapala.wss.service;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WSSUser;
import ht.haapala.wss.repository.PlannedShiftRepository;
import ht.haapala.wss.repository.WSSUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WSSUserServiceImpl implements WSSUserService {

    @Autowired
    WSSUserRepository userRepository;
    @Autowired
    PlannedShiftRepository plannedShiftRepository;

    @Override
    @Transactional(readOnly = true)
    public WSSUser findOne(String username) {
        WSSUser findOne = userRepository.findOne(username);
        if (findOne != null) {
            findOne.setId(findOne.getUsername());
        }
        return findOne;
    }

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

    @Override
    @Transactional(readOnly = false)
    public WSSUser save(WSSUser user) {
        WSSUser save = userRepository.save(user);
        if (save != null) {
            save.setId(save.getUsername());
        }
        return save;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(String username) {
        WSSUser user = userRepository.findOne(username);
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
