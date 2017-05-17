package com.touchsoft.timetracker.util.security;

import com.touchsoft.timetracker.api.dao.UserException;
import com.touchsoft.timetracker.api.model.User;
import com.touchsoft.timetracker.api.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        Set<GrantedAuthority> roles=null;
        try {
            user = userService.getUser(login);
            roles = new HashSet<>();
            if (user.getManager() == null) {
                roles.addAll(AuthorityUtils.createAuthorityList(UserRole.ROLE_ADMIN.name()));
            } else if (user.getCoManager() != null) {
                roles.addAll(AuthorityUtils.createAuthorityList(UserRole.ROLE_ADMIN.name()));
            } else {
                roles.add(new SimpleGrantedAuthority(UserRole.ROLE_USER.name()));
            }
        } catch (UserException e) {
            throw new UsernameNotFoundException("No user with username '" + login + "' found!");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
    }


}