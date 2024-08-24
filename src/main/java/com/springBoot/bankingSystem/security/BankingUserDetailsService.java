package com.springBoot.bankingSystem.security;

import com.springBoot.bankingSystem.entity.BankingUsers;
import com.springBoot.bankingSystem.entity.UserRole;
import com.springBoot.bankingSystem.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class BankingUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public BankingUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//Map the user in my DB to the Spring boot security user with its authorities
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BankingUsers user = this.userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User NOT FOUND!!"));
        return new User(user.getUsername(),user.getPassword(),mapRolesToAuthoritites(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthoritites(List<UserRole> roles){
        return roles.stream()
                .map((role) ->
                   new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
