package com.hostelDatabase.controller;

import com.hostelDatabase.model.Login;
import com.hostelDatabase.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(path = "/add")
    public Login addHostelerLogin(@RequestBody Login login){
        return loginService.save(login);
    }

    @GetMapping(path = "/getAll")
    public List<Login> getHostelerLogin(){
        return loginService.findAll();
    }

    @GetMapping(path = "/get/{id}")
    public Optional<Login> getOneLogin(@PathVariable("id") int id){
        return loginService.findById(id);
    }

    @GetMapping(path = "/get/{hostelerId}/{password}")
    public boolean getHostlerIdLogin(@PathVariable("hostelerId") String hostelerId, @PathVariable("password") String password){
        return loginService.findByHostelerId(hostelerId,password);
    }

    @DeleteMapping(path = "/deleteAll")
    public void deleteLogins(){
        loginService.deleteLogins();
    }
}
