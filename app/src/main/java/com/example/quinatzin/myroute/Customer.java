package com.example.quinatzin.myroute;

/**
 * Created by quinatzin on 4/7/2018.
 */

public class Customer {

    private String userName;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;
    private String price;
    private String day;
    private String address;
    private String notes;


    public Customer() {
    }

    public Customer(String userName, String lastName, String firstName, String phoneNumber, String email, String price, String day, String address, String notes) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.price = price;
        this.day = day;
        this.address = address;
        this.notes = notes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   /*
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    */
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
