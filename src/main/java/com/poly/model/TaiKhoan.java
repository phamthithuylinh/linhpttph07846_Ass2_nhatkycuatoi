package com.poly.model;

import javax.persistence.*;


@Entity
@Table(name = "taikhoan")
public class TaiKhoan {

    @Id
    @Column(name = "TenDangNhap")
    private String TenDangNhap;
    @Column(name = "MatKhau")
    private String MatKhau;
    @Column(name = "Email")
    private String Email;
    @Column(name = "SDT")
    private String SDT;

    public TaiKhoan() {

    }

    public TaiKhoan(String TenDangNhap, String MatKhau, String Email, String SDT){
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.SDT = SDT;
    }

    @Override
    public String toString(){
        return "TaiKhoan{"+
                "TenDangNhap"+TenDangNhap+
                ", MatKhau='" + MatKhau + '\'' +
                ", Email='" + Email + '\'' +
                ", SDT='" + SDT + '\'' +
                '}';
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
