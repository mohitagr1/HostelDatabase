package com.hostelDatabase.service;

import com.hostelDatabase.dao.LoginRepo;
import com.hostelDatabase.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginRepo loginRepo;

    public Login save(Login login){
        return loginRepo.save(login);
    }

    public List<Login> findAll() {
        return loginRepo.findAll();
    }

    public Optional<Login> findById(int id) {
        return loginRepo.findById(id);
    }

    public boolean findByHostelerId(String hostelerId,String password) {
        System.out.println(hostelerId+" "+password);
        Login temp = loginRepo.findByHostelerId(hostelerId);
        System.out.println(temp.getPassword());
        if(temp.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public void deleteLogins() {
        loginRepo.deleteAll();
    }
}
