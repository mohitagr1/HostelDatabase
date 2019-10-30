package com.hostelDatabase.service;

import com.hostelDatabase.dao.HostelRepo;
import com.hostelDatabase.exceptionHandling.ErrorDetails;
import com.hostelDatabase.exceptionHandling.InvalidEntityException;
import com.hostelDatabase.exceptionHandling.RecordNotFoundException;
import com.hostelDatabase.exceptionHandling.SubError;
import com.hostelDatabase.model.Hosteler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HostelService {
    @Autowired
    private HostelRepo hostelRepo;

    private List<SubError> subErrorList;

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
        subErrorList = null;
        ValidationService.validate(hosteler);
        CheckForDuplicates(hosteler);
        return hostelRepo.save(hosteler);
    }

    private void CheckForDuplicates(Hosteler hosteler) throws InvalidEntityException {
        if (hostelRepo.existsHostelerByEmailId(hosteler.getEmailId())) {
            addSubError(new ErrorDetails("Email Id already exists !!", "Hosteler", "Email ID", hosteler.getEmailId()));
        }
        if (hostelRepo.existsHostelerByPhoneNumber(hosteler.getPhoneNumber())) {
            addSubError(new ErrorDetails("Phone Number already exists !!", "Hosteler", "Phone Number", hosteler.getPhoneNumber()));
        }
        if (!subErrorList.isEmpty()) {
            throw new InvalidEntityException("DUPLICATION ERROR !!", subErrorList);
        }
    }

    private void addSubError(SubError subError) {
        if (subErrorList == null) {
            subErrorList = new ArrayList<>();
        }
        subErrorList.add(subError);
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
