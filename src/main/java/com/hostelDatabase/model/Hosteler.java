package com.hostelDatabase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Hosteler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull(message = "First name should not be empty!")
    private String firstName;
    @NotNull(message = "First name should not be empty!")
    private String lastName;
    private String fatherName;
    private String MotherName;
    @NotNull(message = "Phone Number should not be empty!")
    private String phoneNumber;
    private String alternativePhoneNumber;
    @Email(message = "Email entered is invalid.!!")
    private String emailId;
    private String college;
    private String course;
    private int floor;
    private int roomNumber;
    private int age;
    private LocalDate dateOfJoining;
    private LocalDate dateOfBirth;
    private String AddressOfNative;
    private String AddressOfCorrespondence;
    @JsonProperty
    private boolean haveVehicle;
}
