package com.hostelDatabase.service;

import com.hostelDatabase.dao.HostelRepo;
import com.hostelDatabase.model.Hosteler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostelService {
    @Autowired
    private HostelRepo hostelRepo;

    public List<Hosteler> getHostelers() {
        return hostelRepo.findAll();
    }

    public Optional<Hosteler> getHostelerById(int id) {
        return hostelRepo.findById(id);
    }

    public Hosteler addHosteler(Hosteler hosteler) {
        return hostelRepo.save(hosteler);
    }

    public Hosteler saveOrUpdateHosteler(int id, Hosteler hosteler) {
        Optional<Hosteler> hosteler1 = hostelRepo.findById(id);
        if(hosteler1.isPresent()!= true){
            return hostelRepo.save(hosteler);
        }
        hosteler.setId(id);
        return hostelRepo.save(hosteler);
    }

    public boolean deleteHosteler(int id) {
        hostelRepo.deleteById(id);
        return true;
    }
}
