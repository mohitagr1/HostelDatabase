package com.hostelDatabase.service;

import com.hostelDatabase.dao.LoginRepo;
import com.hostelDatabase.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private LoginRepo loginRepo;
    @Override
    public UserDetails loadUserByUsername(String hostelerId) throws UsernameNotFoundException {
        Login login = loginRepo.findByHostelerId(hostelerId);
        return new User(login.getHostelerId(),login.getPassword(),new ArrayList<>());
    }
}
