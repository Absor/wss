package ht.haapala.wss.service;

import ht.haapala.wss.repository.WSSUserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.management.relation.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Heikki Haapala
 */
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private WSSUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
        
        return null;

//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                true,
//                true,
//                true,
//                true,
//                getRolesAsGrantedAuthorities(user.getRoles()));
    }

    private List<GrantedAuthority> getRolesAsGrantedAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return authorities;
    }
}
