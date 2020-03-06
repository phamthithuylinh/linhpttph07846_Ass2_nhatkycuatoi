package com.poly.repository;

import com.poly.model.BaiViet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class BaiVietRepositoryImpl implements BaiVietRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BaiViet> findAllBaiVietByTenDangNhap(String TenDangNhap) {
        String query = "Select c from BaiViet c where c.TenDangNhap = :tenDangNhap";
        TypedQuery<BaiViet> BaiVietTypedQuery=entityManager.createQuery(query.toString(),BaiViet.class);
        BaiVietTypedQuery.setParameter("tenDangNhap",TenDangNhap);
        return BaiVietTypedQuery.getResultList();
    }

    @Override
    public List<BaiViet> findAll() {
        return null;
    }

    @Override
    public BaiViet findById(Long id) {
        return null;
    }

    @Override
    public void save(BaiViet model) {
        entityManager.persist(model);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void update(Long id, BaiViet model) {
        entityManager.merge(model);
    }
}
