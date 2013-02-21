package ht.haapala.wss.service;

import ht.haapala.wss.data.WSSUser;
import ht.haapala.wss.repository.WSSUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WSSUserServiceImpl implements WSSUserService {

    @Autowired
    WSSUserRepository userRepository;

    @Override
    @Transactional(readOnly=true)
    public WSSUser findOne(String username) {
        return userRepository.findOne(username);
    }

    @Override
    @Transactional(readOnly=true)
    public List<WSSUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly=false)
    public WSSUser save(WSSUser user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly=false)
    public void delete(String username) {
        userRepository.delete(username);
    }
}
