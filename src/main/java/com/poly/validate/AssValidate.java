package com.poly.validate;

public class AssValidate {
    public boolean checkSize(int min,int max, int length){
        if(length>max){
            return false;
        }else if(length<min){
            return false;
        }else
            return true;
    }
    public boolean checkMail(String mail){
        String typeMail = "^[\\w-_\\.+]*[\\w-_\\.]\\ @([\\w]+\\.)+[\\w]+[\\w]$";
        if(mail.matches(typeMail)){
            return true;
        }else
            return false;
    }
    public boolean checkSDT(String sdt){
        String typesdt = "^[0-9]*$";
        if(sdt.matches(typesdt)){
            return true;
        }else
            return false;
    }
}
