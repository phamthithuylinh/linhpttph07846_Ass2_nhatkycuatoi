package com.poly.repository;

import com.poly.model.TaiKhoan;

public interface TaiKhoanRepository extends Repository<TaiKhoan> {

    public TaiKhoan findByUsernameAndPassword(String user, String pass);

}
