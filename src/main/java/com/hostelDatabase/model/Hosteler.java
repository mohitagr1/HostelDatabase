package com.hostelDatabase.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Hosteler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fname;
    private String lname;
    private int phonenumber;
//    private String emailId;
//    private String college;
//    private int floor;
//    private int roomNumber;
//    private int age;
//    private Date dateOfJoining;
}
