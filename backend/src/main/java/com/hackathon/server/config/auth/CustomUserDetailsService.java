package com.hackathon.server.config.auth;

import com.hackathon.server.config.error.BadRequestException;
import com.hackathon.server.config.error.ErrorMessageConstants;
import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.user.User;
import com.hackathon.server.repository.user.UserPermissionRepository;
import com.hackathon.server.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
    UserDetailsService is a interface for evaluating and creating account/session for Spring Security.
    It is called when AuthenticationManager calls .authenticate() method
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndEntityStatus(username, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND_BY_USERNAME));
        if(!user.getActive()) {
            throw new BadRequestException(ErrorMessageConstants.USER_NOT_ACTIVE);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user.getId()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(Long userId) {
        return permissionRepository.findPermissionsByUserId(userId)
                .stream()
                .map(permission ->
                        new SimpleGrantedAuthority(permission.getViewName() + "/" + permission.getViewRight() + "/" + permission.getUserViewRole()))
                .collect(Collectors.toList());
    }
}
