package com.hostelDatabase.controller;

import com.hostelDatabase.exceptionHandling.InvalidEntityException;
import com.hostelDatabase.model.Hosteler;
import com.hostelDatabase.model.Login;
import com.hostelDatabase.service.HostelService;
import com.hostelDatabase.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/hosteler")
public class HostelController {
    @Autowired
    private HostelService hostelService;
    @Autowired
    private LoginController loginController;

    @GetMapping(path = "/getAll")
    public List<Hosteler> getHostelers() {
        return hostelService.getHostelers();
    }

    @GetMapping(path = "/get/{id}")
    public Optional<Hosteler> getHostelerById(@PathVariable("id") int id) {
        return hostelService.getHostelerById(id);
    }
    @PostMapping(path = "/add")
    public Hosteler addHosteler(@Valid @RequestBody Hosteler hosteler) throws InvalidEntityException {
//        String hostelerId = String.valueOf((hosteler.getId()));
//        System.out.println(hostelerId);
//        int length = hostelerId.length();
//        switch (length){
//            case 1: hostelerId = "00"+hostelerId;
//                break;
//            case 2: hostelerId = "0"+hostelerId;
//                break;
//            default:
//        }
//        hosteler.setHostelerId("1"+"MH"+hosteler.getDateOfJoining().getYear()+hostelerId);
//        Login login = new Login(hosteler.getHostelerId(),hosteler.getFirstName());
//        loginController.addHostelerLogin(login);
        return hostelService.addHosteler(hosteler);
    }

    @PutMapping(path = "/update/{id}")
    public Hosteler saveOrUpdateHosteler(@PathVariable("id") int id, @Valid @RequestBody Hosteler hosteler) throws InvalidEntityException {
        return hostelService.saveOrUpdateHosteler(id, hosteler);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteHosteler(@PathVariable int id) {
        return hostelService.deleteHosteler(id);
    }
}
