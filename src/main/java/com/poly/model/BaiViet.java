package com.poly.model;

import javax.persistence.*;

@Entity
@Table(name = "baiviet")
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MaBaiViet")
    private Long MaBaiViet;
    @Column(name = "TenDangNhap")
    private String TenDangNhap;
    @Column(name = "Ngay")
    private String Ngay;
    @Column(name = "TieuDe")
    private String TieuDe;
    @Column(name = "TamTrang")
    private String TamTrang;
    @Column(name = "NoiDung")
    private String NoiDung;
    @Column(name = "Anh")
    private String Anh;

    public BaiViet() {

    }

    public BaiViet(Long MaBaiViet, String TenDangNhap,String Ngay, String TieuDe, String TamTrang, String NoiDung, String Anh){
        this.MaBaiViet = MaBaiViet;
        this.TenDangNhap = TenDangNhap;
        this.Ngay = Ngay;
        this.TieuDe = TieuDe;
        this.TamTrang = TamTrang;
        this.NoiDung = NoiDung;
        this.Anh = Anh;

    }

    @Override
    public String toString(){
        return "BaiViet{"+
                "MaBaiViet='"+MaBaiViet+'\'' +
                ", TenTaiKhoan='" + TenDangNhap + '\'' +
                ", Ngay='" + Ngay + '\'' +
                ", TieuDe='" + TieuDe + '\'' +
                ", TamTrang='" + TamTrang + '\'' +
                ", NoiDung='" + NoiDung + '\'' +
                ", Anh='" + Anh + '\'' +
                '}';
    }

    public Long getMaBaiViet() {
        return MaBaiViet;
    }

    public void setMaBaiViet(Long maBaiViet) {
        MaBaiViet = maBaiViet;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getTamTrang() {
        return TamTrang;
    }

    public void setTamTrang(String tamTrang) {
        TamTrang = tamTrang;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }
}
