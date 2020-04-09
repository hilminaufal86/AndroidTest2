package com.example.app_test1;

public class Guest {
    private int attributeID;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

    public Guest(int id, String email, String first, String last, String avatarAddr) {
        this.attributeID = id;
        this.email = email;
        this.firstName = first;
        this.lastName = last;
        this.avatar = avatarAddr;
    }

    public int getAttributeID() {
        return attributeID;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
