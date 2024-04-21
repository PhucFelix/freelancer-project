package com.spring.security.services;

import com.spring.security.entities.UserEntity;
import com.spring.security.model.UserDetail;
import com.spring.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity;
        try{
             userEntity = userRepository.getUserEntitiesByUserName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Not found user by username: " + username));

        }catch(Exception e){
            throw new UsernameNotFoundException("Dont connect service");
        }

        return new UserDetail(userEntity);
    }
}
