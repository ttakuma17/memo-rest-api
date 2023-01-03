package com.rtjavamemoapp.domain.service;

import com.rtjavamemoapp.domain.model.Memo;
import com.rtjavamemoapp.infrastructure.mapper.MemoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoServiceImpl implements MemoService{
    private MemoMapper memoMapper;

    public MemoServiceImpl(MemoMapper memoMapper){
        this.memoMapper = memoMapper;
    }

    @Override
    public List<Memo> findAll(){
        return memoMapper.findAll();
    }
}
