package com.example.project;

public class userData {
    String name;
    String email;
    String gst;
    String pass;
    String conpass;
    String mobile;

    public userData(){

    }

    public userData(String name, String email, String gst, String pass, String conpass, String mobile) {
        this.name = name;
        this.email = email;
        this.gst = gst;
        this.pass = pass;
        this.conpass = conpass;
        this.mobile = mobile;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConpass() {
        return conpass;
    }

    public void setConpass(String conpass) {
        this.conpass = conpass;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



}
