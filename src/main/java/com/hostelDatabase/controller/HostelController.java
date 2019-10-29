package com.hostelDatabase.controller;

import com.hostelDatabase.exceptionHandling.InvalidEntityException;
import com.hostelDatabase.model.Hosteler;
import com.hostelDatabase.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class HostelController {
    @Autowired
    private HostelService hostelService;

    @GetMapping(path = "/hostelers")
    public List<Hosteler> getHostelers() {
        return hostelService.getHostelers();
    }

    @GetMapping(path = "/hosteler/{id}")
    public Optional<Hosteler> getHostelerById(@PathVariable("id") int id) {
        return hostelService.getHostelerById(id);
    }

    @PostMapping(path = "/hosteler")
    public Hosteler addHosteler(@Valid @RequestBody Hosteler hosteler) throws InvalidEntityException {
        return hostelService.addHosteler(hosteler);
    }

    @PutMapping(path = "/hosteler/{id}")
    public Hosteler saveOrUpdateHosteler(@PathVariable("id") int id, @Valid @RequestBody Hosteler hosteler) throws InvalidEntityException {
        return hostelService.saveOrUpdateHosteler(id, hosteler);
    }

    @DeleteMapping(path = "/hosteler/{id}")
    public boolean deleteHosteler(@PathVariable int id) {
        return hostelService.deleteHosteler(id);
    }
}
