package com.rtjavamemoapp.domain.service;

import com.rtjavamemoapp.application.resources.MemoForm;
import com.rtjavamemoapp.domain.exception.ResourceNotFoundException;
import com.rtjavamemoapp.domain.model.Memo;
import com.rtjavamemoapp.infrastructure.mapper.MemoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
    
    private final MemoMapper memoMapper;
    
    @Override
    public List<Memo> findAll() {
        return memoMapper.findAll();
    }

    @Override
    public Memo findById(int id) {
        return this.memoMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("指定したIDに紐づくメモは存在しません。"));
    }

    @Override
    public void createMemo(MemoForm form) {
        memoMapper.createMemo(form);
    }

    @Override
    public void deleteMemo(int id) {
        memoMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("指定したIDに紐づくメモは存在しません。"));
        memoMapper.deleteMemo(id);
    }

    @Override
    public void updateMemo(int id, MemoForm form) {
        memoMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("指定したIDに紐づくメモは存在しません。"));
        memoMapper.updateMemo(id, form);
    }
}
