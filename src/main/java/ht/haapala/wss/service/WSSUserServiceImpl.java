package ht.haapala.wss.service;

import ht.haapala.wss.repository.WSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class WSSUserServiceImpl implements WSSUserService {

    @Autowired
    WSSUserRepository userRepository;
}
