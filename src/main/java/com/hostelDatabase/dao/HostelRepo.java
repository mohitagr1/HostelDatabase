package com.hostelDatabase.dao;

import com.hostelDatabase.model.Hosteler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostelRepo extends JpaRepository<Hosteler, Integer> {
    boolean existsHostelerByPhoneNumber(String phoneNumber);
    boolean existsHostelerByEmailId(String emailId);
}
