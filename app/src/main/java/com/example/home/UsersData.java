package com.example.home;

public class UsersData {
    private String userId;
    private String email;
    private String password;
    private String Check;
    private String Phone;
    private String send;

    public  UsersData(String userId,String email,String password,String Check,String Phone,String send){
        this.userId=userId;
        this.email=email;
        this.password=password;
        this.Check=Check;
        this.Phone=Phone;
        this.send=send;
    }

    public UsersData(){

    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheck() {
        return Check;
    }

    public void setCheck(String check) {
        Check = check;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }
}
