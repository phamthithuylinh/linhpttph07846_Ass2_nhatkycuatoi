package com.poly.service;

import com.poly.model.BaiViet;

import java.util.List;
import java.util.Optional;

public interface BaiVietService {
    List<BaiViet> findAll();

    Optional<BaiViet> findById(Long id);

    void save(BaiViet baiViet);

    void remove(Long id);
    void update(BaiViet baiViet);

    public List<BaiViet> findAllBaiVietByTenDangNhap(String TenDangNhap);
}
