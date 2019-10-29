package com.hostelDatabase.service;

import com.hostelDatabase.dao.HostelRepo;
import com.hostelDatabase.exceptionHandling.InvalidEntityException;
import com.hostelDatabase.exceptionHandling.RecordNotFoundException;
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
        Optional<Hosteler> hosteler = hostelRepo.findById(id);
        if (!hosteler.isPresent()) {
            throw new RecordNotFoundException("Invalid Hosteler id : " + id);
        }
        return hosteler;
    }

    public Hosteler addHosteler(Hosteler hosteler) throws InvalidEntityException {
        ValidationService.validate(hosteler);
        return hostelRepo.save(hosteler);
    }

    public Hosteler saveOrUpdateHosteler(int id, Hosteler hosteler) throws InvalidEntityException {
        if (!hostelRepo.existsById(id)) {
            throw new RecordNotFoundException("Invalid Hosteler id : " + id);
        }
        ValidationService.validate(hosteler);
        hosteler.setId(id);
        return hostelRepo.save(hosteler);
    }

    public boolean deleteHosteler(int id) {
        if (!hostelRepo.existsById(id)) {
            throw new RecordNotFoundException("Invalid Hostler id :" + id);
        }
        hostelRepo.deleteById(id);
        return true;
    }
}
