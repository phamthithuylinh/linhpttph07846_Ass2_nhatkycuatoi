package com.poly.service;

import com.poly.model.TaiKhoan;
import com.poly.repository.TaiKhoanRepository;
import com.poly.repository.TaiKhoanRepositoryPaing;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private TaiKhoanRepository TaiKhoanRepository;
    @Autowired
    private TaiKhoanRepositoryPaing taiKhoanRepositoryPaing;
    @Override
    public List<TaiKhoan> findAll() {
        return TaiKhoanRepository.findAll();
    }

    @Override
    public TaiKhoan findById(Long id) {
        return TaiKhoanRepository.findById(id);
    }

    @Override
    public void save(TaiKhoan taiKhoan) { taiKhoanRepositoryPaing.save(taiKhoan);

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public TaiKhoan findByUsernameAndPassword(String user, String pass) {
        return TaiKhoanRepository.findByUsernameAndPassword(user, pass);
    }
}
