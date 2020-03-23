package com.hostelDatabase.service;

import com.hostelDatabase.controller.LoginController;
import com.hostelDatabase.dao.HostelRepo;
import com.hostelDatabase.exceptionHandling.ErrorDetails;
import com.hostelDatabase.exceptionHandling.InvalidEntityException;
import com.hostelDatabase.exceptionHandling.RecordNotFoundException;
import com.hostelDatabase.exceptionHandling.SubError;
import com.hostelDatabase.model.Hosteler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hostelDatabase.model.Login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HostelService {
    @Autowired
    private HostelRepo hostelRepo;
    @Autowired
    private LoginController loginController;

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
        System.out.println("validate OK");
        CheckForDuplicates(hosteler);

        hostelRepo.save(hosteler);
        System.out.println("Check Passed --> "+hosteler.getHostelerId());
        String hostelerId = String.valueOf((hosteler.getId()));
        System.out.println(hostelerId);
        int length = hostelerId.length();
        switch (length){
            case 1: hostelerId = "00"+hostelerId;
                break;
            case 2: hostelerId = "0"+hostelerId;
                break;
            default:
        }
        hosteler.setHostelerId("1"+"MH"+hosteler.getDateOfJoining().getYear()+hostelerId);
        hostelRepo.save(hosteler);
        Login login = new Login(hosteler.getId(),hosteler.getHostelerId(),hosteler.getFirstName());
        System.out.println("login login --->"+login.getHostelerId()+" "+login.getPassword());
        loginController.addHostelerLogin(login);
        System.out.println("Check Passed --> "+hosteler.getHostelerId());
        return hosteler;
    }

    private void CheckForDuplicates(Hosteler hosteler) throws InvalidEntityException {
        if (hostelRepo.existsHostelerByEmailId(hosteler.getEmailId())) {
            addSubError(new ErrorDetails("Email Id already exists !!", "Hosteler", "Email ID", hosteler.getEmailId()));
        }
        if (hostelRepo.existsHostelerByPhoneNumber(hosteler.getPhoneNumber())) {
            addSubError(new ErrorDetails("Phone Number already exists !!", "Hosteler", "Phone Number", hosteler.getPhoneNumber()));
        }
        if (subErrorList!=null && !subErrorList.isEmpty()) {
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
//        hosteler.setId(id);
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
