package com.example.ahendrivingschool;

import android.widget.Button;
import android.widget.EditText;

public class SchoolDataHolder {

    String s_name,s_address,s_pincode,s_mail,s_mobile;
    String  s_admin;

    public SchoolDataHolder(String s_name, String s_address, String s_pincode, String s_mail, String s_mobile,String s_admin) {
        this.s_name = s_name;
        this.s_address = s_address;
        this.s_pincode = s_pincode;
        this.s_mail = s_mail;
        this.s_mobile = s_mobile;
        this.s_admin="1";
    }

    public String getS_admin() {
        return s_admin;
    }

    public void setS_admin(String s_admin) {
        this.s_admin = s_admin;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getS_pincode() {
        return s_pincode;
    }

    public void setS_pincode(String s_pincode) {
        this.s_pincode = s_pincode;
    }

    public String getS_mail() {
        return s_mail;
    }

    public void setS_mail(String s_mail) {
        this.s_mail = s_mail;
    }

    public String getS_mobile() {
        return s_mobile;
    }

    public void setS_mobile(String s_mobile) {
        this.s_mobile = s_mobile;
    }
}
