package com.hostelDatabase.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Login{
    @Id
    private Integer id;
    private String hostelerId;
    @NotNull
    private String password;

    public Login(){

    }

    public Login(Integer id, String hostelerId, String password) {
        this.id = id;
        this.hostelerId = hostelerId;
        this.password = password;
    }
}
