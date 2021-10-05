package com.example.softwareengineeringbackend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    private String guid;
    private int number;
    private String gender;
    private String givenName;
    private String surname;
    private String emailAddress;

    public Student() {
        // JPA Only
    }

    public Student(String guid, int number, String gender, String givenName, String surname, String emailAddress) {
        this.guid = guid;
        this.number = number;
        this.gender = gender;
        this.givenName = givenName;
        this.surname = surname;
        this.emailAddress = emailAddress;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
