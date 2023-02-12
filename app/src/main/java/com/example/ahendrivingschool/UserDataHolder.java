package com.example.ahendrivingschool;

import java.util.Date;

public class UserDataHolder {

    String u_name,u_mail,u_birth;
    String u_mobile;
    String u_user, u_pancard,u_aadharcard,u_address;

    public UserDataHolder(String u_name, String u_mail, String u_birth, String u_mobile,String u_user,String u_address, String u_aadharcard,String u_pancard) {
        this.u_name = u_name;
        this.u_mail = u_mail;
        this.u_birth = u_birth;
        this.u_mobile = u_mobile;
        this.u_user="2";
        this.u_pancard = u_pancard;
        this.u_aadharcard = u_aadharcard;
        this.u_address = u_address;
    }


    public String getU_pancard() {
        return u_pancard;
    }

    public void setU_pancard(String u_pancard) {
        this.u_pancard = u_pancard;
    }

    public String getU_aadharcard() {
        return u_aadharcard;
    }

    public void setU_aadharcard(String u_aadharcard) {
        this.u_aadharcard = u_aadharcard;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getU_user() {
        return u_user;
    }

    public void setU_user(String u_user) {
        this.u_user = u_user;
    }

    public String getU_birth() {
        return u_birth;
    }

    public void setU_birth(String u_birth) {
        this.u_birth = u_birth;
    }

    public String getU_mobile() {
        return u_mobile;
    }

    public void setU_mobile(String u_mobile) {
        this.u_mobile = u_mobile;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_mail() {
        return u_mail;
    }

    public void setU_mail(String u_mail) {
        this.u_mail = u_mail;
    }




}