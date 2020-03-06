package com.poly.repository;

import com.poly.model.BaiViet;

import java.util.List;

public interface BaiVietRepository extends Repository<BaiViet> {
    public List<BaiViet> findAllBaiVietByTenDangNhap(String TenDangNhap);
}
