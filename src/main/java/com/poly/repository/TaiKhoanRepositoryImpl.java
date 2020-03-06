package com.poly.repository;

import com.poly.model.TaiKhoan;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TaiKhoanRepositoryImpl implements TaiKhoanRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TaiKhoan> findAll() {
        String query = "select c from TaiKhoan c";
        TypedQuery<TaiKhoan> accountTypedQuery=entityManager.createQuery(query,TaiKhoan.class);
        return accountTypedQuery.getResultList();
    }

    @Override
    public TaiKhoan findById(Long id) {
        return null;
    }

    @Override
    public void save(TaiKhoan model) {
        entityManager.persist(model);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void update(Long id, TaiKhoan model) {

    }

    @Override
    public TaiKhoan findByUsernameAndPassword(String user, String pass) {
       try {
           String query = "Select c from TaiKhoan c where c.TenDangNhap = :user and c.MatKhau = :pass";
           TypedQuery<TaiKhoan> accountTypedQuery=entityManager.createQuery(query.toString(),TaiKhoan.class);
           accountTypedQuery.setParameter("user",user);
           accountTypedQuery.setParameter("pass",pass);
           return accountTypedQuery.getSingleResult();
       }catch (NoResultException nre){
           return null;
       }
    }

}
