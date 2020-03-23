package com.hostelDatabase.dao;

import com.hostelDatabase.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Login,Integer> {
    Login findByHostelerId(String hostelerId);
}
