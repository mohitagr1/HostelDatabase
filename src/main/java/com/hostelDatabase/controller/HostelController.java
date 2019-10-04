package com.hostelDatabase.controller;

import com.hostelDatabase.dao.HostelRepo;
import com.hostelDatabase.model.Hosteler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HostelController {
    @Autowired
    HostelRepo hostelRepo;

    @GetMapping(path = "/hostelers")
    public List<Hosteler> getHostelers(){
        return hostelRepo.findAll();
    }

    @GetMapping(path = "/hosteler/{id}")
    public Optional<Hosteler> getHostelerById(@PathVariable("id") int id){
        return hostelRepo.findById(id);
    }

    @PostMapping(path = "/hosteler")
    public Hosteler addHosteler(@RequestBody Hosteler hosteler){
        hostelRepo.save(hosteler);
        return hosteler;
    }

    @PutMapping(path = "/hosteler")
    public Hosteler saveOrUpdateHosteler(@RequestBody Hosteler hosteler){
        hostelRepo.save(hosteler);
        return hosteler;
    }

    @DeleteMapping(path = "/hosteler/{id}")
    public boolean deleteHosteler(@PathVariable int id){
        hostelRepo.deleteById(id);
        return true;
    }

}
