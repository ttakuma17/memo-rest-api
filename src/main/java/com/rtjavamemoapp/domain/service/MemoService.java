package com.rtjavamemoapp.domain.service;

import com.rtjavamemoapp.application.resources.MemoForm;
import com.rtjavamemoapp.domain.model.Memo;

import java.util.List;

public interface MemoService {

    List<Memo> findAll();

    Memo findById(int id);

    void createMemo(MemoForm form);
}
