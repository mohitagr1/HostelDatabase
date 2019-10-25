package com.hostelDatabase.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.hostelDatabase.model.Hosteler;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;

@Validated
class ValidationService {

    static void validate(Hosteler hosteler) {
        hosteler.setPhoneNumber(validatePhoneNumber(hosteler.getPhoneNumber(), true));
        hosteler.setAlternativePhoneNumber(validatePhoneNumber(hosteler.getAlternativePhoneNumber(), false));
    }

    private static String validatePhoneNumber(String phoneNumber, boolean checkNull) throws ConstraintViolationException {
        if (phoneNumber == null) {
            if (checkNull) {
                throw new NullPointerException("The phone number supplied was null!");
            } else {
                return null;
            }
        } else {
            PhoneNumber phoneNumber1 = new PhoneNumber();
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            try {
                phoneNumber1 = phoneNumberUtil.parse(phoneNumber, "IN");
            } catch (NumberParseException e) {
                System.err.println("NumberParseException : " + e.toString());
                throw new RuntimeException(e);
            }
            if (phoneNumberUtil.isValidNumber(phoneNumber1)) {
                return phoneNumberUtil.format(phoneNumber1, PhoneNumberUtil.PhoneNumberFormat.E164);
            } else {
//                System.err.println("Phone Number is not valid !! ");
                throw new ConstraintViolationException("Phone Number is not valid!!", null);
//                throw new RuntimeException("Phone Number is not valid!!");
            }
        }
    }
}
