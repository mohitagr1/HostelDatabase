package com.hostelDatabase.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.hostelDatabase.exceptionHandling.InvalidEntityException;
import com.hostelDatabase.model.Hosteler;
import org.springframework.validation.annotation.Validated;


@Validated
class ValidationService {

    static void validate(Hosteler hosteler) throws InvalidEntityException {
        hosteler.setPhoneNumber(validatePhoneNumber(hosteler.getPhoneNumber(), true));
        hosteler.setAlternativePhoneNumber(validatePhoneNumber(hosteler.getAlternativePhoneNumber(), false));
        validateEmailId(hosteler.getEmailId());
    }

    private static void validateEmailId(String emailId) {

    }

    private static String validatePhoneNumber(String phoneNumber, boolean checkNull) throws InvalidEntityException {
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
                if (checkNull) {
                    throw new InvalidEntityException("Phone Number is invalid : " + phoneNumber, null);
                } else {
                    throw new InvalidEntityException("Alternative Phone Number is invalid : " + phoneNumber, null);
                }
            }
        }
    }
}
