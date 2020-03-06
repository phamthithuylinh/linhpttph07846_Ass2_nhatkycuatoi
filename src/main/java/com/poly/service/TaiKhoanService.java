package com.poly.service;

import com.poly.model.TaiKhoan;

import java.util.List;

public interface TaiKhoanService {
    List<TaiKhoan> findAll();

    TaiKhoan findById(Long id);

    void save(TaiKhoan customer);

    void remove(Long id);

    TaiKhoan findByUsernameAndPassword(String user, String pass);
}
