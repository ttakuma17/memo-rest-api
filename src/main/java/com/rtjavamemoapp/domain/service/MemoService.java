package com.rtjavamemoapp.domain.service;

import com.rtjavamemoapp.domain.model.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoService {

    List<Memo> findAll();
    
    Optional<Memo> findById(int id);
}
