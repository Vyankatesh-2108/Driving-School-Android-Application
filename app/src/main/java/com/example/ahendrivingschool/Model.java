package com.example.ahendrivingschool;

public class Model {

    String  s_address, s_mail, s_mobile, s_name, s_pincode , s_profile;

    public Model() {
    }

    public Model(String s_address, String s_mail, String s_mobile, String s_name, String s_pincode, String s_profile) {
        this.s_address = s_address;
        this.s_mail = s_mail;
        this.s_mobile = s_mobile;
        this.s_name = s_name;
        this.s_pincode = s_pincode;
        this.s_profile = s_profile;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
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

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_pincode() {
        return s_pincode;
    }

    public void setS_pincode(String s_pincode) {
        this.s_pincode = s_pincode;
    }

    public  String getS_profile(){
        return  s_profile;
    }

    public  void setS_profile(String s_profile){
        this.s_profile=s_profile;
    }

}

