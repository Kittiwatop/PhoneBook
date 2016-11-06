package com.example.phonebook.model;

/**
 * Created by ON-ONE on 10/23/2016.
 */
public class Contact {
    private String mName;
    private String mPhone;
    private String mImage;

    public Contact(String name, String phone, String image) {
        this.mName = name;
        this.mPhone = phone;
        this.mImage = image;
    }




    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    @Override//เพิ่มเข้ามา
    public String toString() {
        return mName;
    }


    public String getImage() {
        return mImage;
    }
}
