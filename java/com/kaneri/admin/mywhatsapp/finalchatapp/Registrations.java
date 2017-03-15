package com.kaneri.admin.mywhatsapp.finalchatapp;

/**
 * Created by dell on 13/03/2017.
 */

public class Registrations {

    private String email;
    private String phone_number;
    private String name;

    public Registrations(){
        //Required empty constructor
    }
    public Registrations(String email,String phonenumber,String name)
    {
        this.email = email;
        this.phone_number = phonenumber;
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName(){return name;}

    public String getPhonenumber()
    {
        return phone_number;
    }

    public void setName(String name){this.name = name;}
    public void setEmail(String email){
        this.email = email;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phone_number = phonenumber;
    }
}
